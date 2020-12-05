package com.app.module.master.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.app.beans.AssignTaskToFlatBean;
import com.app.beans.AssignTaskToFlatResponseBean;
import com.app.beans.BuildingBean;
import com.app.beans.FlatBean;
import com.app.beans.FlatTypeBean;
import com.app.beans.ProjectBean;
import com.app.beans.ResponseBean;
import com.app.beans.TaskBean;
import com.app.beans.WorkTypeBean;
import com.app.constant.MessageConstant;
import com.app.entities.AssignTaskToFlat;
import com.app.entities.Building;
import com.app.entities.Flat;
import com.app.entities.FlatType;
import com.app.entities.Project;
import com.app.entities.Task;
import com.app.entities.WorkType;
import com.app.exception.CheckListAppException;
import com.app.module.master.repository.IAssignTaskToFlatDao;
import com.app.module.master.repository.IFlatDao;
import com.app.module.master.service.IAssignTaskToFlatService;

/**
 * @author Piyush.Thakre
 *
 */
@Service
public class AssignTaskToFlatServiceImpl implements IAssignTaskToFlatService {

	/*
	 * @Autowired IAssignTaskToFlatValidation assignTaskToFlatValidation;
	 */

	@Autowired
	IAssignTaskToFlatDao assignTaskToFlatDao;
	
	@Autowired
	IFlatDao flatDao;

	@Override
	public ResponseBean insertOrUpdateAssignTaskToFlat(AssignTaskToFlatBean assignTaskToFlatBean)
			throws CheckListAppException {
		// assignTaskToFlatValidation.checkDuplicateAssignTaskToFlat(assignTaskToFlatBean);
		List<AssignTaskToFlat> assignTaskToFlats=new ArrayList<AssignTaskToFlat>();
		

		List<TaskBean> taskBeans = assignTaskToFlatBean.getTasks();
		if (assignTaskToFlatBean.getFlatId() != 0) {
			
			taskBeans.forEach(taskBean -> {
				AssignTaskToFlat assignTaskToFlat = new AssignTaskToFlat();
				BeanUtils.copyProperties(assignTaskToFlatBean, assignTaskToFlat);
				
				Project project = new Project();
				project.setProjectId(assignTaskToFlatBean.getProjectId());
				assignTaskToFlat.setProject(project);
				Building building = new Building();
				building.setBuildingId(assignTaskToFlatBean.getBuildingId());
				assignTaskToFlat.setBuilding(building);

				WorkType workType = new WorkType();
				workType.setWorkTypeId(assignTaskToFlatBean.getWorkTypeId());
				assignTaskToFlat.setWorkType(workType);

				FlatType flatType = new FlatType();
				flatType.setFlatTypeId(assignTaskToFlatBean.getFlatTypeId());
				assignTaskToFlat.setFlatType(flatType);
				
				Flat flat = new Flat();
				flat.setFlatId(assignTaskToFlatBean.getFlatId());
				assignTaskToFlat.setFlat(flat);
				
				Task task = new Task();
				BeanUtils.copyProperties(taskBean, task);
				assignTaskToFlat.setTask(task);
				assignTaskToFlats.add(assignTaskToFlat);
			});
			
		} else {
			List<Flat> flats = flatDao.getFlatByFlatTypeId(assignTaskToFlatBean.getFlatTypeId());
			flats.stream().forEach(flat -> {
				
				taskBeans.forEach(taskBean -> {
					AssignTaskToFlat assignTaskToFlat = new AssignTaskToFlat();
					BeanUtils.copyProperties(assignTaskToFlatBean, assignTaskToFlat);
					
					Project project = new Project();
					project.setProjectId(assignTaskToFlatBean.getProjectId());
					assignTaskToFlat.setProject(project);
					Building building = new Building();
					building.setBuildingId(assignTaskToFlatBean.getBuildingId());
					assignTaskToFlat.setBuilding(building);

					WorkType workType = new WorkType();
					workType.setWorkTypeId(assignTaskToFlatBean.getWorkTypeId());
					assignTaskToFlat.setWorkType(workType);

					FlatType flatType = new FlatType();
					flatType.setFlatTypeId(assignTaskToFlatBean.getFlatTypeId());
					assignTaskToFlat.setFlatType(flatType);
					
					assignTaskToFlat.setFlat(flat);
					
					Task task = new Task();
					BeanUtils.copyProperties(taskBean, task);
					assignTaskToFlat.setTask(task);
					assignTaskToFlats.add(assignTaskToFlat);
				});
			});
		}
		assignTaskToFlatDao.saveAll(assignTaskToFlats);
		return ResponseBean.builder().message(MessageConstant.DATA_SAVE_SUCCESS)
				.messageDescription(MessageConstant.ASSIGN_TASK_TO_FLAT).status(true)
				.satusCode(HttpStatus.CREATED.value()).hasError(false).build();
	}

	@Override
	public ResponseBean getActiveAssignTaskToFlats() throws CheckListAppException {
		try {
			return ResponseBean.builder()
					.data(prepareAssignTaskToFlatBeansFromAssignTaskToFlat(
							assignTaskToFlatDao.getActiveAssignTaskToFlats()))
					.status(true).hasError(false).message(MessageConstant.SUCCESS_MESSAGE).build();
		} catch (Exception e) {
			throw new CheckListAppException(CheckListAppException.SERVER_ERROR, MessageConstant.SERVER_ERROR_MESSAGE,
					MessageConstant.QUERY_FETCH_EXCPTION);
		}
	}
	
	@Override
	public ResponseBean getTaskByFlatIdAndWorktype(Long flatId,Long workTypeId) throws CheckListAppException {
		try {
			return ResponseBean.builder()
					.data(prepareTaskBeansFromAssignTaskToFlat(
							assignTaskToFlatDao.getTaskByFlatIdAndWorktype(flatId, workTypeId)))
					.status(true).hasError(false).message(MessageConstant.SUCCESS_MESSAGE).build();
		} catch (Exception e) {
			throw new CheckListAppException(CheckListAppException.SERVER_ERROR, MessageConstant.SERVER_ERROR_MESSAGE,
					MessageConstant.QUERY_FETCH_EXCPTION);
		}
	}

	@Override
	public ResponseBean getAssignTaskToFlats() throws CheckListAppException {
		try {
			return ResponseBean.builder()
					.data(prepareAssignTaskToFlatBeansFromAssignTaskToFlat(assignTaskToFlatDao.findAll()))
					.status(true).hasError(false).message(MessageConstant.SUCCESS_MESSAGE).build();
		} catch (Exception e) {
			throw new CheckListAppException(CheckListAppException.SERVER_ERROR, MessageConstant.SERVER_ERROR_MESSAGE,
					MessageConstant.QUERY_FETCH_EXCPTION);
		}
	}

	@Override
	public ResponseBean getAssignTaskToFlatById(Long assignTaskToFlatId) throws CheckListAppException {
		try {
			return ResponseBean.builder()
					.data(prepareAssignTaskToFlatBeansFromAssignTaskToFlat(assignTaskToFlatDao.getAssignTaskToFlatByAssignTaskToFlatId(assignTaskToFlatId))).status(true)
					.hasError(false).message(MessageConstant.SUCCESS_MESSAGE).build();
		} catch (Exception e) {
			throw new CheckListAppException(CheckListAppException.SERVER_ERROR, MessageConstant.SERVER_ERROR_MESSAGE,
					MessageConstant.QUERY_FETCH_EXCPTION);
		}
	}
	
	private List<AssignTaskToFlatResponseBean> prepareAssignTaskToFlatBeansFromAssignTaskToFlat(
			List<AssignTaskToFlat> allAssignTaskToFlats) {
		List<AssignTaskToFlatResponseBean> assignTaskToFlatBeans = new ArrayList<AssignTaskToFlatResponseBean>();
		allAssignTaskToFlats.forEach(assignTaskToFlat -> {
			AssignTaskToFlatResponseBean assignTaskToFlatResponseBean = new AssignTaskToFlatResponseBean();
			BeanUtils.copyProperties(assignTaskToFlat, assignTaskToFlatResponseBean);
			ProjectBean projectBean = new ProjectBean();
			BeanUtils.copyProperties(assignTaskToFlat.getProject(), projectBean);
			assignTaskToFlatResponseBean.setProject(projectBean);
			BuildingBean buildingBean = new BuildingBean();
			BeanUtils.copyProperties(assignTaskToFlat.getBuilding(), buildingBean);
			assignTaskToFlatResponseBean.setBuilding(buildingBean);
			
			FlatTypeBean flatTypeBean=new FlatTypeBean();
			BeanUtils.copyProperties(assignTaskToFlat.getFlatType(), flatTypeBean);
			assignTaskToFlatResponseBean.setFlatType(flatTypeBean);
			
			FlatBean flatBean = new FlatBean();
			BeanUtils.copyProperties(assignTaskToFlat.getFlat(), flatBean);
			assignTaskToFlatResponseBean.setFlat(flatBean);
			
			WorkTypeBean workTypeBean = new WorkTypeBean();
			BeanUtils.copyProperties(assignTaskToFlat.getWorkType(), workTypeBean);
			assignTaskToFlatResponseBean.setWorkTypeBean(workTypeBean);
			
			TaskBean taskBean = new TaskBean();
			BeanUtils.copyProperties(assignTaskToFlat.getTask(), taskBean);
			assignTaskToFlatResponseBean.setTaskBean(taskBean);
			assignTaskToFlatBeans.add(assignTaskToFlatResponseBean);
		});
		return assignTaskToFlatBeans;
	}
	
	private List<TaskBean> prepareTaskBeansFromAssignTaskToFlat(
			List<AssignTaskToFlat> allAssignTaskToFlats) {
		List<TaskBean> taskBeans = new ArrayList<TaskBean>();
		allAssignTaskToFlats.forEach(assignTaskToFlat -> {
			TaskBean taskBean = new TaskBean();
			BeanUtils.copyProperties(assignTaskToFlat.getTask(), taskBean);
			taskBeans.add(taskBean);
		});
		return taskBeans;
	}
}
