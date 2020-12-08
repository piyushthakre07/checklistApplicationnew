package com.app.module.master.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.app.beans.AssignFlatToOwnerResponseBean;
import com.app.beans.AssignWorkToContractorResponseBean;
import com.app.beans.BuildingBean;
import com.app.beans.CheckListOperationBean;
import com.app.beans.CheckListOperationResponseBean;
import com.app.beans.FlatBean;
import com.app.beans.FlatTypeBean;
import com.app.beans.FloorBean;
import com.app.beans.OwnerBean;
import com.app.beans.ProjectBean;
import com.app.beans.ResponseBean;
import com.app.beans.RoomBean;
import com.app.beans.TaskBean;
import com.app.beans.WorkTypeBean;
import com.app.constant.MessageConstant;
import com.app.entities.Building;
import com.app.entities.CheckListOperation;
import com.app.entities.Flat;
import com.app.entities.FlatType;
import com.app.entities.Owner;
import com.app.entities.Project;
import com.app.entities.Room;
import com.app.entities.Task;
import com.app.entities.WorkType;
import com.app.exception.CheckListAppException;
import com.app.module.master.repository.ICheckListOperationDao;
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
	IAssignWorkToContractorService assignWorkToContractorService;

	@Override
	public ResponseBean insertOrUpdateCheckListOperation(CheckListOperationBean checkListOperationBean)
			throws CheckListAppException {

		checkListOperationBean.getRoomIds().stream().forEach(roomid -> {
			CheckListOperation checkListOperation = new CheckListOperation();
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

			Flat flat = new Flat();
			flat.setFlatId(checkListOperationBean.getFlatId());
			checkListOperation.setFlat(flat);

			FlatType flatType = new FlatType();
			flatType.setFlatTypeId(checkListOperationBean.getFlatTypeId());
			checkListOperation.setFlatType(flatType);

			if (checkListOperationBean.getOwnerId() != null) {
				Owner owner = new Owner();
				owner.setOwnerId(checkListOperationBean.getOwnerId());
				checkListOperation.setOwner(owner);
			}
			Task task = new Task();
			task.setTaskId(checkListOperationBean.getTaskId());
			checkListOperation.setTask(task);
			Room room = new Room();
			room.setRoomId(roomid);
			checkListOperation.setRoom(room);
			checkListOperationDao.save(checkListOperation);
		});

		return ResponseBean.builder().message(MessageConstant.DATA_SAVE_SUCCESS)
				.messageDescription(MessageConstant.PROJECT_SAVE_SUCCESS_MESSAGE).status(true)
				.satusCode(HttpStatus.CREATED.value()).hasError(false).build();
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
	public ResponseBean getCheckListOperationByFlatIdAndWorkTypeId(Long flatId,Long workTypeId)
			throws CheckListAppException {
		try {
			return ResponseBean.builder()
					.data(prepareCheckListOperationResponseBeanFromCheckListOperations(
							checkListOperationDao.getCheckListOperationReport(null, null, flatId, workTypeId)))
					.status(true).hasError(false).message(MessageConstant.SUCCESS_MESSAGE).build();
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
					.data(prepareCheckListOperationResponseBeanFromCheckListOperations(checkListOperationDao.getCheckListOperationReport(checkListOperationBean.getProjectId(),
							checkListOperationBean.getBuildingId(), checkListOperationBean.getFlatId(),
							checkListOperationBean.getWorkTypeId())))
					.status(true).hasError(false).message(MessageConstant.SUCCESS_MESSAGE).build();
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

			if (checkListOperation.getOwner() != null) {
				OwnerBean ownerBean = new OwnerBean();
				BeanUtils.copyProperties(checkListOperation.getOwner(), ownerBean);
				checkListOperationBean.setOwner(ownerBean);
			}

			if(checkListOperation.getFlatType()!=null) {
			FlatTypeBean flatType = new FlatTypeBean();
			BeanUtils.copyProperties(checkListOperation.getFlatType(), flatType);
			checkListOperationBean.setFlatType(flatType);
			}

			WorkTypeBean workType = new WorkTypeBean();
			BeanUtils.copyProperties(checkListOperation.getWorkType(), workType);
			checkListOperationBean.setWorkType(workType);

			TaskBean task = new TaskBean();
			BeanUtils.copyProperties(checkListOperation.getTask(), task);
			checkListOperationBean.setTask(task);

			RoomBean room = new RoomBean();
			BeanUtils.copyProperties(checkListOperation.getRoom(), room);
			checkListOperationBean.setRoom(room);

			try {
				List<AssignWorkToContractorResponseBean> list = assignWorkToContractorService
						.getAssignWorkToContractorByFlatIdNWorkType(checkListOperation.getFlat().getFlatId(),
								checkListOperation.getWorkType().getWorkTypeId());
				if (list != null && !list.isEmpty())
					checkListOperationBean.setContractor(list.get(0).getContractor());
			} catch (CheckListAppException e) {

			}
			checkListOperationBeans.add(checkListOperationBean);
		});
		return checkListOperationBeans;
	}
}
