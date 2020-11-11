package com.app.module.master.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.app.beans.CheckListOperationBean;
import com.app.beans.ResponseBean;
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

	@Override
	public ResponseBean insertOrUpdateCheckListOperation(CheckListOperationBean checkListOperationBean) throws CheckListAppException {
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

		Flat flat=new Flat();
		flat.setFlatId(checkListOperationBean.getFlatId());
		checkListOperation.setFlat(flat);
		
		FlatType flatType = new FlatType();
		flatType.setFlatTypeId(checkListOperationBean.getFlatTypeId());
		checkListOperation.setFlatType(flatType);
		
		if(checkListOperationBean.getOwnerId()!=null) {
		Owner owner = new Owner();
		owner.setOwnerId(checkListOperationBean.getOwnerId());
		checkListOperation.setOwner(owner);
		}
		
		Task task=new Task();
		task.setTaskId(checkListOperationBean.getTaskId());
		checkListOperation.setTask(task);
		
		Room room=new Room();
		room.setRoomId(checkListOperationBean.getRoomId());
		checkListOperation.setRoom(room);
		
		checkListOperationDao.save(checkListOperation);
		
		return ResponseBean.builder().message(MessageConstant.DATA_SAVE_SUCCESS)
				.messageDescription(MessageConstant.PROJECT_SAVE_SUCCESS_MESSAGE).status(true)
				.satusCode(HttpStatus.CREATED.value()).hasError(false).build();
	}

	@Override
	public ResponseBean getCheckListOperations() throws CheckListAppException {
		try {
			return ResponseBean.builder().data(prepareCheckListOperationsBeanFromCheckListOperations(checkListOperationDao.findAll())).status(true)
					.hasError(false).message(MessageConstant.SUCCESS_MESSAGE).build();
		} catch (Exception e) {
			throw new CheckListAppException(CheckListAppException.SERVER_ERROR, MessageConstant.SERVER_ERROR_MESSAGE,
					MessageConstant.QUERY_FETCH_EXCPTION);
		}
	}

	@Override
	public ResponseBean getCheckListOperationByCheckListOperationId(Long checkListOperationId) throws CheckListAppException {
		try {
			return ResponseBean.builder()
					.data(prepareCheckListOperationsBeanFromCheckListOperations(checkListOperationDao.getCheckListOperationByCheckListOperationId(checkListOperationId))).status(true)
					.hasError(false).message(MessageConstant.SUCCESS_MESSAGE).build();
		} catch (Exception e) {
			throw new CheckListAppException(CheckListAppException.SERVER_ERROR, MessageConstant.SERVER_ERROR_MESSAGE,
					MessageConstant.QUERY_FETCH_EXCPTION);
		}
	}
	
	private List<CheckListOperationBean> prepareCheckListOperationsBeanFromCheckListOperations(List<CheckListOperation> allCheckListOperations) {
		List<CheckListOperationBean> checkListOperationBeans = new ArrayList<CheckListOperationBean>();
		allCheckListOperations.forEach(checkListOperation -> {
			CheckListOperationBean checkListOperationBean = new CheckListOperationBean();
			BeanUtils.copyProperties(checkListOperation, checkListOperationBean);
			checkListOperationBeans.add(checkListOperationBean);
		});
		return checkListOperationBeans;
	}
}
