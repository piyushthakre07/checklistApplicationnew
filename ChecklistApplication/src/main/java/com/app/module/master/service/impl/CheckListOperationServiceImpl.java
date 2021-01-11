package com.app.module.master.service.impl;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.zip.DataFormatException;
import java.util.zip.Deflater;
import java.util.zip.Inflater;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.app.beans.AssignWorkToContractorResponseBean;
import com.app.beans.BuildingBean;
import com.app.beans.CheckListOperationBean;
import com.app.beans.CheckListOperationDefectImageUploadResponseBean;
import com.app.beans.CheckListOperationDefectRequestBean;
import com.app.beans.CheckListOperationNewResponseBean;
import com.app.beans.CheckListOperationResponseBean;
import com.app.beans.CheckListOperationTaskResponseBean;
import com.app.beans.FlatBean;
import com.app.beans.FlatTypeBean;
import com.app.beans.FloorBean;
import com.app.beans.OwnerBean;
import com.app.beans.ProjectBean;
import com.app.beans.ResponseBean;
import com.app.beans.RoomBean;
import com.app.beans.RoomResponseBean;
import com.app.beans.TaskBean;
import com.app.beans.TaskResponseBean;
import com.app.beans.WorkTypeBean;
import com.app.beans.WorkTypeResponseBean;
import com.app.constant.MessageConstant;
import com.app.entities.Building;
import com.app.entities.CheckListOperation;
import com.app.entities.CheckListOperationDefectImageUpload;
import com.app.entities.CheckListOperationTaskDetails;
import com.app.entities.Flat;
import com.app.entities.FlatType;
import com.app.entities.Owner;
import com.app.entities.Project;
import com.app.entities.Room;
import com.app.entities.Task;
import com.app.entities.WorkType;
import com.app.exception.CheckListAppException;
import com.app.module.master.repository.ICheckListOperationDao;
import com.app.module.master.repository.ICheckListOperationDefectImageUploadDao;
import com.app.module.master.repository.ICheckListOperationTaskDetailsDao;
import com.app.module.master.repository.IFlatDao;
import com.app.module.master.service.IAssignRoomToFlatService;
import com.app.module.master.service.IAssignTaskToFlatService;
import com.app.module.master.service.IAssignWorkToContractorService;
import com.app.module.master.service.ICheckListOperationService;

/**
 * @author Piyush.Thakre
 * @since 13 sep 2020
 *
 */
@Service
public class CheckListOperationServiceImpl implements ICheckListOperationService {

	@Autowired
	ICheckListOperationDao checkListOperationDao;

	@Autowired
	IFlatDao flatDao;

	@Autowired
	ICheckListOperationTaskDetailsDao checkListOperationTaskDetailsDao;

	@Autowired
	ICheckListOperationDefectImageUploadDao checkListOperationDefectImageUploadDao;

	@Autowired
	IAssignWorkToContractorService assignWorkToContractorService;

	@Autowired
	IAssignRoomToFlatService assignRoomToFlatService;

	@Autowired
	IAssignTaskToFlatService assignTaskToFlatService;

	@Value("${defectBaseFileStrorePath}")
	private String defectBaseFileStrorePath;

	@Override
	public ResponseBean insertOrUpdateCheckListOperation(CheckListOperationBean checkListOperationBean)
			throws CheckListAppException {

		checkListOperationBean.getTaskDetails().stream().forEach(taskDetail -> {
			List<CheckListOperation> checkListOperationList = checkListOperationDao.getCheckListOperationReport(
					checkListOperationBean.getProjectId(), checkListOperationBean.getBuildingId(),
					checkListOperationBean.getFlatId(), checkListOperationBean.getWorkTypeId());
			CheckListOperation checkListOperation = new CheckListOperation();
			if (checkListOperationList == null || checkListOperationList.isEmpty()) {

				BeanUtils.copyProperties(checkListOperationBean, checkListOperation);

				Project project = new Project();
				project.setProjectId(checkListOperationBean.getProjectId());
				checkListOperation.setProject(project);

				Building building = new Building();
				building.setBuildingId(checkListOperationBean.getBuildingId());
				checkListOperation.setBuilding(building);

				WorkType workType = new WorkType();
				workType.setWorkTypeId(checkListOperationBean.getWorkTypeId());
				checkListOperation.setWorkType(workType);

				Flat flat = (flatDao.getFlatByFlatId(checkListOperationBean.getFlatId())).get(0);
				flat.setFlatId(checkListOperationBean.getFlatId());
				checkListOperation.setFlat(flat);

				FlatType flatType = new FlatType();
				flatType.setFlatTypeId(flat.getFlatType().getFlatTypeId());
				checkListOperation.setFlatType(flatType);

				if (checkListOperationBean.getOwnerId() != null) {
					Owner owner = new Owner();
					owner.setOwnerId(checkListOperationBean.getOwnerId());
					checkListOperation.setOwner(owner);
				}

				checkListOperationDao.save(checkListOperation);
			} else {
				checkListOperation = checkListOperationList.get(0);
			}

			List<CheckListOperationTaskDetails> checkListOperationTaskDetailsList = checkListOperationTaskDetailsDao
					.getCheckListOperationTaskDetailsByTaskIdNRoomIdNCheckListOperationId(
							checkListOperationBean.getTaskId(), taskDetail.getRoomId(),
							checkListOperation.getCheckListOperationId());
			CheckListOperationTaskDetails checkListOperationTaskDetails = null;
			if (checkListOperationTaskDetailsList.isEmpty()) {
				checkListOperationTaskDetails = new CheckListOperationTaskDetails();
				Task task = new Task();
				task.setTaskId(checkListOperationBean.getTaskId());
				checkListOperationTaskDetails.setTask(task);

				Room room = new Room();
				room.setRoomId(taskDetail.getRoomId());
				checkListOperationTaskDetails.setRoom(room);
				checkListOperationTaskDetails.setCheckListOperation(checkListOperation);

			} else {
				checkListOperationTaskDetails = checkListOperationTaskDetailsList.get(0);
			}
			BeanUtils.copyProperties(taskDetail, checkListOperationTaskDetails);
			if (checkListOperationBean.is_owner())
				checkListOperationTaskDetails.setOwnerCheck(true);
			else
				checkListOperationTaskDetails.setUserCheck(true);
			checkListOperationTaskDetailsDao.save(checkListOperationTaskDetails);
		});

		return ResponseBean.builder().message(MessageConstant.DATA_SAVE_SUCCESS)
				.messageDescription(MessageConstant.PROJECT_SAVE_SUCCESS_MESSAGE).status(true)
				.satusCode(HttpStatus.CREATED.value()).hasError(false).build();
	}

	@Override
	public ResponseBean insertOrUpdateCheckListOperationDefect(
			CheckListOperationDefectRequestBean checkListOperationDefectRequestBean) throws CheckListAppException {

		List<CheckListOperation> checkListOperationList = checkListOperationDao.getCheckListOperationReport(
				checkListOperationDefectRequestBean.getProjectId(), checkListOperationDefectRequestBean.getBuildingId(),
				checkListOperationDefectRequestBean.getFlatId(), checkListOperationDefectRequestBean.getWorkTypeId());
		CheckListOperation checkListOperation = new CheckListOperation();
		if (checkListOperationList == null || checkListOperationList.isEmpty()) {

			BeanUtils.copyProperties(checkListOperationDefectRequestBean, checkListOperation);

			Project project = new Project();
			project.setProjectId(checkListOperationDefectRequestBean.getProjectId());
			checkListOperation.setProject(project);

			Building building = new Building();
			building.setBuildingId(checkListOperationDefectRequestBean.getBuildingId());
			checkListOperation.setBuilding(building);

			WorkType workType = new WorkType();
			workType.setWorkTypeId(checkListOperationDefectRequestBean.getWorkTypeId());
			checkListOperation.setWorkType(workType);
			Long flatId = checkListOperationDefectRequestBean.getFlatId();
			Flat flat = (flatDao.getFlatByFlatId(flatId)).get(0);
			checkListOperation.setFlat(flat);

			FlatType flatType = new FlatType();
			flatType.setFlatTypeId(flat.getFlatType().getFlatTypeId());
			checkListOperation.setFlatType(flatType);

			if (checkListOperationDefectRequestBean.getOwnerId() != null) {
				Owner owner = new Owner();
				owner.setOwnerId(checkListOperationDefectRequestBean.getOwnerId());
				checkListOperation.setOwner(owner);

			}

			checkListOperationDao.save(checkListOperation);
		} else {
			checkListOperation = checkListOperationList.get(0);
		}

		List<CheckListOperationTaskDetails> checkListOperationTaskDetailsList = checkListOperationTaskDetailsDao
				.getCheckListOperationTaskDetailsByTaskIdNRoomIdNCheckListOperationId(
						checkListOperationDefectRequestBean.getTaskId(),
						checkListOperationDefectRequestBean.getRoomId(), checkListOperation.getCheckListOperationId());
		CheckListOperationTaskDetails checkListOperationTaskDetails = null;
		if (checkListOperationTaskDetailsList == null || checkListOperationTaskDetailsList.isEmpty()) {

			checkListOperationTaskDetails = new CheckListOperationTaskDetails();
			BeanUtils.copyProperties(checkListOperationDefectRequestBean, checkListOperationTaskDetails);

			Task task = new Task();
			task.setTaskId(checkListOperationDefectRequestBean.getTaskId());
			checkListOperationTaskDetails.setTask(task);

			Room room = new Room();
			room.setRoomId(checkListOperationDefectRequestBean.getRoomId());
			checkListOperationTaskDetails.setRoom(room);

			if (checkListOperationDefectRequestBean.isOwner())
				checkListOperationTaskDetails.setFaultOwner(true);
			else
				checkListOperationTaskDetails.setFaultUser(true);

			if (checkListOperationDefectRequestBean.isOwner())
				checkListOperationTaskDetails.setFaultOwnerRemark(checkListOperationDefectRequestBean.getFaultRemark());
			else
				checkListOperationTaskDetails.setFaultUserRemark(checkListOperationDefectRequestBean.getFaultRemark());

			checkListOperationTaskDetails.setCheckListOperation(checkListOperation);

		} else {
			checkListOperationTaskDetails = checkListOperationTaskDetailsList.get(0);

		}
		checkListOperationTaskDetailsDao.save(checkListOperationTaskDetails);

		if (checkListOperationDefectRequestBean.getUploadImages() != null) {

			for (MultipartFile file : checkListOperationDefectRequestBean.getUploadImages()) {
				CheckListOperationDefectImageUpload checkListOperationDefectImageUpload = new CheckListOperationDefectImageUpload();
				String fileName = file.getOriginalFilename();
				checkListOperationDefectImageUpload.setName(fileName);
				checkListOperationDefectImageUpload.setType(file.getContentType());
				try {
					String dir = checkListOperationDefectRequestBean.getFlatId() + ""
							+ checkListOperationDefectRequestBean.getRoomId() + ""
							+ checkListOperationDefectRequestBean.getTaskId() + "";
					String path = defectBaseFileStrorePath + dir;
					saveFile(path, fileName, file);
					checkListOperationDefectImageUpload.setPath(path);
					checkListOperationDefectImageUpload.setCheckListOperationTaskDetails(checkListOperationTaskDetails);
					checkListOperationDefectImageUploadDao.save(checkListOperationDefectImageUpload);
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}

		return ResponseBean.builder().message(MessageConstant.DATA_SAVE_SUCCESS)
				.messageDescription(MessageConstant.PROJECT_SAVE_SUCCESS_MESSAGE).status(true)
				.satusCode(HttpStatus.CREATED.value()).hasError(false).build();
	}

	public void saveFile(String uploadDir, String fileName, MultipartFile multipartFile) throws IOException {
		Path uploadPath = Paths.get(uploadDir);

		try (InputStream inputStream = multipartFile.getInputStream()) {
			if (!Files.exists(uploadPath)) {
				Files.createDirectories(uploadPath);
			}
			Path filePath = uploadPath.resolve(fileName);
			Files.copy(inputStream, filePath, StandardCopyOption.REPLACE_EXISTING);
		} catch (IOException e) {
			throw new IOException("Could not save image file: " + fileName, e);
		}

	}

	@Override
	public ResponseBean getCheckListOperations() throws CheckListAppException {
		try {
			return ResponseBean.builder()
					.data(prepareCheckListOperationsBeanFromCheckListOperations(checkListOperationDao.findAll()))
					.status(true).hasError(false).message(MessageConstant.SUCCESS_MESSAGE).build();
		} catch (Exception e) {
			throw new CheckListAppException(CheckListAppException.SERVER_ERROR, MessageConstant.SERVER_ERROR_MESSAGE,
					MessageConstant.QUERY_FETCH_EXCPTION);
		}
	}

	@Override
	public ResponseBean getCheckListOperationByCheckListOperationId(Long checkListOperationId)
			throws CheckListAppException {
		try {
			return ResponseBean.builder()
					.data(prepareCheckListOperationsBeanFromCheckListOperations(
							checkListOperationDao.getCheckListOperationByCheckListOperationId(checkListOperationId)))
					.status(true).hasError(false).message(MessageConstant.SUCCESS_MESSAGE).build();
		} catch (Exception e) {
			throw new CheckListAppException(CheckListAppException.SERVER_ERROR, MessageConstant.SERVER_ERROR_MESSAGE,
					MessageConstant.QUERY_FETCH_EXCPTION);
		}
	}

	@Override
	public ResponseBean getCheckListOperationTaskDetailsByFlatIdAndAndWorTypeAndTaskIdAndRoomId(Long flatId,
			Long workTypeId, Long taskId) throws CheckListAppException {
		try {
			return ResponseBean.builder().data(prepareCheckListOperationResponseBeanFromCheckListOperationsByTaskId(
					checkListOperationTaskDetailsDao.getCheckListOperationTaskDetailsByFlatIdAndAndWorTypeAndTaskId(
							flatId, workTypeId, taskId),
					taskId, null)).status(true).hasError(false).message(MessageConstant.SUCCESS_MESSAGE).build();
		} catch (Exception e) {
			throw new CheckListAppException(CheckListAppException.SERVER_ERROR, MessageConstant.SERVER_ERROR_MESSAGE,
					MessageConstant.QUERY_FETCH_EXCPTION);
		}
	}

	@Override
	public List<ByteArrayResource> getDefectCheckListOperationByFlatIdAndWorTypeAndTaskIdAndRoomId(Long flatId,
			Long workTypeId, Long taskId, Long roomId) throws CheckListAppException {
		try {

			List<CheckListOperationDefectImageUploadResponseBean> list = getImageFromCheckListOperation(
					checkListOperationDefectImageUploadDao
							.getCheckListOperationDefectImageUploadByFlatIdAndAndWorTypeAndTaskId(flatId, workTypeId,
									taskId, roomId),
					taskId, roomId);
			List<ByteArrayResource> byteArrayResourceList = new ArrayList<ByteArrayResource>();
			for (CheckListOperationDefectImageUploadResponseBean checkListOperationDefectImageUploadResponseBean : list) {
				ByteArrayResource resource = new ByteArrayResource(
						Files.readAllBytes(Paths.get(checkListOperationDefectImageUploadResponseBean.getImageName())));
			}
			return byteArrayResourceList;
		} catch (Exception e) {
			throw new CheckListAppException(CheckListAppException.SERVER_ERROR, MessageConstant.SERVER_ERROR_MESSAGE,
					MessageConstant.QUERY_FETCH_EXCPTION);
		}
	}

	@Override
	public ResponseBean getCheckListOperationReport(CheckListOperationBean checkListOperationBean)
			throws CheckListAppException {
		try {
			return ResponseBean.builder()
					.data(prepareCheckListOperationResponseBeanFromCheckListOperations(
							checkListOperationDao.getCheckListOperationReport(checkListOperationBean.getProjectId(),
									checkListOperationBean.getBuildingId(), checkListOperationBean.getFlatId(),
									checkListOperationBean.getWorkTypeId())))
					.status(true).hasError(false).message(MessageConstant.SUCCESS_MESSAGE).build();
		} catch (Exception e) {
			throw new CheckListAppException(CheckListAppException.SERVER_ERROR, MessageConstant.SERVER_ERROR_MESSAGE,
					MessageConstant.QUERY_FETCH_EXCPTION);
		}
	}

	@Override
	public ResponseBean getCheckListOperationReportNew(CheckListOperationBean checkListOperationBean)
			throws CheckListAppException {
		try {
			CheckListOperationNewResponseBean checkListOperationNewResponseBean = new CheckListOperationNewResponseBean();
			checkListOperationNewResponseBean
					.setHeaderRooms(assignRoomToFlatService.getRoomBeansByFlatId(checkListOperationBean.getFlatId()));
			Flat flat = (flatDao.getFlatByFlatId(checkListOperationBean.getFlatId())).get(0);

			if (flat != null) {
				ProjectBean projectBean = new ProjectBean();
				BeanUtils.copyProperties(flat.getProject(), projectBean);
				checkListOperationNewResponseBean.setProject(projectBean);

				BuildingBean buildingBean = new BuildingBean();
				BeanUtils.copyProperties(flat.getBuilding(), buildingBean);
				checkListOperationNewResponseBean.setBuilding(buildingBean);

				if (flat.getOwner() != null) {
					OwnerBean ownerBean = new OwnerBean();
					BeanUtils.copyProperties(flat.getOwner(), ownerBean);
					checkListOperationNewResponseBean.setOwner(ownerBean);
				}

				FlatBean flatBean = new FlatBean();
				BeanUtils.copyProperties(flat, flatBean);
				checkListOperationNewResponseBean.setFlat(flatBean);

				List<WorkTypeResponseBean> workTypeResponseBeanList = assignTaskToFlatService
						.getWorkTypeResponseBean(checkListOperationBean.getFlatId());
				workTypeResponseBeanList.stream().forEach(workTypeResponseBean -> {
					try {
						List<TaskResponseBean> tasks = assignTaskToFlatService.getTaskResponseBeanByFlatIdAndWorktype(
								checkListOperationBean.getFlatId(), workTypeResponseBean.getWorkTypeId());
						tasks.stream().forEach(task -> {
							List<RoomResponseBean> roomResponseBeans = new ArrayList<RoomResponseBean>();

							checkListOperationNewResponseBean.getHeaderRooms().stream().forEach(headerRoom -> {
								List<CheckListOperationTaskDetails> checkListOperationTaskDetailsList = checkListOperationTaskDetailsDao
										.getCheckListOperationTaskDetailsByFlatIdAndAndWorTypeAndTaskIdAndRoomId(
												checkListOperationBean.getFlatId(),
												workTypeResponseBean.getWorkTypeId(), task.getTaskId(),
												headerRoom.getRoomId());
								RoomResponseBean roomResponseBean = new RoomResponseBean();
								if (checkListOperationTaskDetailsList != null
										&& !checkListOperationTaskDetailsList.isEmpty()) {
									CheckListOperationTaskDetails checkListOperationTaskDetails = checkListOperationTaskDetailsList
											.get(0);
									BeanUtils.copyProperties(checkListOperationTaskDetails, roomResponseBean);
									roomResponseBean.setRoomId(checkListOperationTaskDetails.getRoom().getRoomId());
									roomResponseBean.setOwnerChecked(checkListOperationTaskDetails.getOwnerCheck());
									roomResponseBean.setUserChecked(checkListOperationTaskDetails.getUserCheck());
									if ((checkListOperationTaskDetails.getOwnerCheck() == null)
											|| (!checkListOperationTaskDetails.getOwnerCheck()))
										roomResponseBean.setFaultOwner(checkListOperationTaskDetails.getFaultOwner());
									if (checkListOperationTaskDetails.getUserCheck() == null
											|| !checkListOperationTaskDetails.getUserCheck())
										roomResponseBean.setFaultUser(checkListOperationTaskDetails.getFaultUser());

									roomResponseBeans.add(roomResponseBean);

								}
							});
							task.setRooms(roomResponseBeans);
						});

						workTypeResponseBean.setTasks(tasks);
					} catch (CheckListAppException e) {
					}
				});
				checkListOperationNewResponseBean.setWorkTypes(workTypeResponseBeanList);
			}

			return ResponseBean.builder().data(checkListOperationNewResponseBean).status(true).hasError(false)
					.message(MessageConstant.SUCCESS_MESSAGE).build();
		} catch (Exception e) {
			throw new CheckListAppException(CheckListAppException.SERVER_ERROR, MessageConstant.SERVER_ERROR_MESSAGE,
					MessageConstant.QUERY_FETCH_EXCPTION);
		}
	}

	private List<CheckListOperationBean> prepareCheckListOperationsBeanFromCheckListOperations(
			List<CheckListOperation> allCheckListOperations) {
		List<CheckListOperationBean> checkListOperationBeans = new ArrayList<CheckListOperationBean>();
		allCheckListOperations.forEach(checkListOperation -> {
			CheckListOperationBean checkListOperationBean = new CheckListOperationBean();
			BeanUtils.copyProperties(checkListOperation, checkListOperationBean);
			checkListOperationBeans.add(checkListOperationBean);
		});
		return checkListOperationBeans;
	}

	private List<CheckListOperationResponseBean> prepareCheckListOperationResponseBeanFromCheckListOperations(
			List<CheckListOperation> allCheckListOperations) throws CheckListAppException {
		List<CheckListOperationResponseBean> checkListOperationBeans = new ArrayList<CheckListOperationResponseBean>();
		allCheckListOperations.forEach(checkListOperation -> {
			CheckListOperationResponseBean checkListOperationBean = new CheckListOperationResponseBean();
			BeanUtils.copyProperties(checkListOperation, checkListOperationBean);

			ProjectBean projectBean = new ProjectBean();
			BeanUtils.copyProperties(checkListOperation.getProject(), projectBean);
			checkListOperationBean.setProject(projectBean);

			BuildingBean buildingBean = new BuildingBean();
			BeanUtils.copyProperties(checkListOperation.getBuilding(), buildingBean);
			checkListOperationBean.setBuilding(buildingBean);

			FlatBean flatBean = new FlatBean();
			BeanUtils.copyProperties(checkListOperation.getFlat(), flatBean);
			checkListOperationBean.setFlat(flatBean);

			FloorBean floorBean = new FloorBean();
			BeanUtils.copyProperties(checkListOperation.getFlat().getFloor(), floorBean);
			checkListOperationBean.setFloor(floorBean);

			if (checkListOperation.getOwner() != null) {
				OwnerBean ownerBean = new OwnerBean();
				BeanUtils.copyProperties(checkListOperation.getOwner(), ownerBean);
				checkListOperationBean.setOwner(ownerBean);
			}

			if (checkListOperation.getFlatType() != null) {
				FlatTypeBean flatType = new FlatTypeBean();
				BeanUtils.copyProperties(checkListOperation.getFlatType(), flatType);
				checkListOperationBean.setFlatType(flatType);
			}

			WorkTypeBean workType = new WorkTypeBean();
			BeanUtils.copyProperties(checkListOperation.getWorkType(), workType);
			checkListOperationBean.setWorkType(workType);

			List<CheckListOperationTaskResponseBean> checkListOperationTaskResponseBeanList = new ArrayList<CheckListOperationTaskResponseBean>();

			checkListOperation.getCheckListOperationTaskDetails().stream().forEach(checkListOperationTaskDetails -> {
				CheckListOperationTaskResponseBean checkListOperationTaskResponseBean = new CheckListOperationTaskResponseBean();

				BeanUtils.copyProperties(checkListOperationTaskDetails, checkListOperationTaskResponseBean);

				TaskBean task = new TaskBean();
				BeanUtils.copyProperties(checkListOperationTaskDetails.getTask(), task);
				checkListOperationTaskResponseBean.setTask(task);

				RoomBean room = new RoomBean();
				BeanUtils.copyProperties(checkListOperationTaskDetails.getRoom(), room);
				checkListOperationTaskResponseBean.setRoom(room);

				try {
					List<AssignWorkToContractorResponseBean> list = assignWorkToContractorService
							.getAssignWorkToContractorByFlatIdNWorkType(checkListOperation.getFlat().getFlatId(),
									checkListOperation.getWorkType().getWorkTypeId());

					if (list != null && !list.isEmpty())
						checkListOperationTaskResponseBean.setContractor(list.get(0).getContractor());

				} catch (CheckListAppException e) {

				}
				checkListOperationTaskResponseBeanList.add(checkListOperationTaskResponseBean);

			});
			checkListOperationBean.setCheckListOperationTaskResponseBeanList(checkListOperationTaskResponseBeanList);

			checkListOperationBeans.add(checkListOperationBean);
		});
		return checkListOperationBeans;
	}

	private List<CheckListOperationResponseBean> prepareCheckListOperationResponseBeanFromCheckListOperationsByTaskId(
			List<CheckListOperation> allCheckListOperations, Long taskId, Long roomId) throws CheckListAppException {
		List<CheckListOperationResponseBean> checkListOperationBeans = new ArrayList<CheckListOperationResponseBean>();
		CheckListOperation checkListOperation = allCheckListOperations.get(0);
		CheckListOperationResponseBean checkListOperationBean = new CheckListOperationResponseBean();
		BeanUtils.copyProperties(checkListOperation, checkListOperationBean);

		ProjectBean projectBean = new ProjectBean();
		BeanUtils.copyProperties(checkListOperation.getProject(), projectBean);
		checkListOperationBean.setProject(projectBean);

		BuildingBean buildingBean = new BuildingBean();
		BeanUtils.copyProperties(checkListOperation.getBuilding(), buildingBean);
		checkListOperationBean.setBuilding(buildingBean);

		FlatBean flatBean = new FlatBean();
		BeanUtils.copyProperties(checkListOperation.getFlat(), flatBean);
		checkListOperationBean.setFlat(flatBean);

		FloorBean floorBean = new FloorBean();
		BeanUtils.copyProperties(checkListOperation.getFlat().getFloor(), floorBean);
		checkListOperationBean.setFloor(floorBean);

		if (checkListOperation.getOwner() != null) {
			OwnerBean ownerBean = new OwnerBean();
			BeanUtils.copyProperties(checkListOperation.getOwner(), ownerBean);
			checkListOperationBean.setOwner(ownerBean);
		}

		if (checkListOperation.getFlatType() != null) {
			FlatTypeBean flatType = new FlatTypeBean();
			BeanUtils.copyProperties(checkListOperation.getFlatType(), flatType);
			checkListOperationBean.setFlatType(flatType);
		}

		WorkTypeBean workType = new WorkTypeBean();
		BeanUtils.copyProperties(checkListOperation.getWorkType(), workType);
		checkListOperationBean.setWorkType(workType);

		List<CheckListOperationTaskResponseBean> checkListOperationTaskResponseBeanList = new ArrayList<CheckListOperationTaskResponseBean>();

		checkListOperation.getCheckListOperationTaskDetails().stream().forEach(checkListOperationTaskDetails -> {

			if (checkListOperationTaskDetails.getTask().getTaskId() == taskId
					&& (roomId == null || checkListOperationTaskDetails.getRoom().getRoomId() == roomId)) {

				CheckListOperationTaskResponseBean checkListOperationTaskResponseBean = new CheckListOperationTaskResponseBean();
				BeanUtils.copyProperties(checkListOperationTaskDetails, checkListOperationTaskResponseBean);

				TaskBean task = new TaskBean();
				BeanUtils.copyProperties(checkListOperationTaskDetails.getTask(), task);
				checkListOperationTaskResponseBean.setTask(task);

				RoomBean room = new RoomBean();
				BeanUtils.copyProperties(checkListOperationTaskDetails.getRoom(), room);
				checkListOperationTaskResponseBean.setRoom(room);

				checkListOperationTaskResponseBean.setUserChecked(checkListOperationTaskDetails.getUserCheck());
				checkListOperationTaskResponseBean.setOwnerChecked(checkListOperationTaskDetails.getOwnerCheck());
				checkListOperationTaskResponseBean.setFaultUser(checkListOperationTaskDetails.getFaultUser());
				checkListOperationTaskResponseBean.setFaultOwner(checkListOperationTaskDetails.getFaultOwner());
				try {
					List<AssignWorkToContractorResponseBean> list = assignWorkToContractorService
							.getAssignWorkToContractorByFlatIdNWorkType(checkListOperation.getFlat().getFlatId(),
									checkListOperation.getWorkType().getWorkTypeId());

					if (list != null && !list.isEmpty())
						checkListOperationTaskResponseBean.setContractor(list.get(0).getContractor());

				} catch (CheckListAppException e) {

				}
				checkListOperationTaskResponseBeanList.add(checkListOperationTaskResponseBean);
			}

		});
		checkListOperationBean.setCheckListOperationTaskResponseBeanList(checkListOperationTaskResponseBeanList);

		checkListOperationBeans.add(checkListOperationBean);

		return checkListOperationBeans;
	}

	private List<CheckListOperationDefectImageUploadResponseBean> getImageFromCheckListOperation(
			List<CheckListOperationDefectImageUpload> allCheckListOperationDefectImageUploads, Long taskId, Long roomId)
			throws CheckListAppException {
		List<CheckListOperationDefectImageUploadResponseBean> checkListOperationDefectImageUploads = new ArrayList<CheckListOperationDefectImageUploadResponseBean>();
		allCheckListOperationDefectImageUploads.stream().forEach(checkListOperationDefectImageUpload -> {
			CheckListOperationDefectImageUploadResponseBean checkListOperationDefectImageUploadResponseBean = new CheckListOperationDefectImageUploadResponseBean();
			checkListOperationDefectImageUploadResponseBean.setImageName(checkListOperationDefectImageUpload.getPath()
					+ "/" + checkListOperationDefectImageUpload.getName());
			checkListOperationDefectImageUploads.add(checkListOperationDefectImageUploadResponseBean);
		});
		return checkListOperationDefectImageUploads;
	}

	public static byte[] compressBytes(byte[] data) {

		Deflater deflater = new Deflater();

		deflater.setInput(data);

		deflater.finish();

		ByteArrayOutputStream outputStream = new ByteArrayOutputStream(data.length);

		byte[] buffer = new byte[1024];

		while (!deflater.finished()) {

			int count = deflater.deflate(buffer);

			outputStream.write(buffer, 0, count);

		}

		try {

			outputStream.close();

		} catch (IOException e) {

		}

		System.out.println("Compressed Image Byte Size - " + outputStream.toByteArray().length);

		return outputStream.toByteArray();

	}

	// uncompress the image bytes before returning it to the angular application

	public static byte[] decompressBytes(byte[] data) {

		Inflater inflater = new Inflater();

		inflater.setInput(data);

		ByteArrayOutputStream outputStream = new ByteArrayOutputStream(data.length);

		byte[] buffer = new byte[1024];

		try {

			while (!inflater.finished()) {

				int count = inflater.inflate(buffer);

				outputStream.write(buffer, 0, count);

			}

			outputStream.close();

		} catch (IOException ioe) {

		} catch (DataFormatException e) {

		}

		return outputStream.toByteArray();

	}
}
