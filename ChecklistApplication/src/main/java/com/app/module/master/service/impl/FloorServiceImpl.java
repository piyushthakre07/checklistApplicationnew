package com.app.module.master.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.app.beans.BuildingBean;
import com.app.beans.FloorBean;
import com.app.beans.FloorRequestBean;
import com.app.beans.ProjectBean;
import com.app.beans.ResponseBean;
import com.app.constant.MessageConstant;
import com.app.entities.Building;
import com.app.entities.Floor;
import com.app.entities.Project;
import com.app.exception.CheckListAppException;
import com.app.module.master.repository.IFloorDao;
import com.app.module.master.service.IFloorService;

/**
 * @author Piyush.Thakre
 *
 */
@Service
public class FloorServiceImpl implements IFloorService {

	/*
	 * @Autowired IFloorValidation floorValidation;
	 */

	@Autowired
	IFloorDao floorDao;

	@Override
	public ResponseBean insertOrUpdateFloor(FloorBean floorBean) throws CheckListAppException {
		// floorValidation.checkDuplicateFloor(floorBean);
		Floor floor = new Floor();
		BeanUtils.copyProperties(floorBean, floor);
		Project project = new Project();
		project.setProjectId(floorBean.getProjectId());
		floor.setProject(project);
		Building building = new Building();
		building.setBuildingId(floorBean.getBuildingId());
		floor.setBuilding(building);
		floorDao.save(floor);
		return ResponseBean.builder().message(MessageConstant.DATA_SAVE_SUCCESS)
				.messageDescription(MessageConstant.FLOOR_SAVE_SUCCESS_MESSAGE).status(true)
				.satusCode(HttpStatus.CREATED.value()).hasError(false).build();
	}

	@Override
	public ResponseBean insertMultipleFloor(FloorRequestBean floorRequestBean) throws CheckListAppException {
		// floorValidation.checkDuplicateFloor(floorBean);
		try {
			floorRequestBean.getFloorBeanList().forEach(floorBean -> {
				Floor floor = new Floor();
				floor.setFloorName(floorBean.getFloorName());
				floor.setActive(floorBean.isActive());
				floor.setDescription(floorBean.getDescription());
				//BeanUtils.copyProperties(floorBean, floor);
				Project project = new Project();
				project.setProjectId(floorRequestBean.getProjectId());
				floor.setProject(project);
				Building building = new Building();
				building.setBuildingId(floorBean.getBuildingId());
				floor.setBuilding(building);
				floorDao.save(floor);
			});
			return ResponseBean.builder().message(MessageConstant.DATA_SAVE_SUCCESS)
					.messageDescription(MessageConstant.FLOOR_SAVE_SUCCESS_MESSAGE).status(true)
					.satusCode(HttpStatus.CREATED.value()).hasError(false).build();
		} catch (Exception e) {
			throw new CheckListAppException(CheckListAppException.SERVER_ERROR, MessageConstant.SERVER_ERROR_MESSAGE,
					MessageConstant.DATA_SAVE_EXCPTION);
		}
	}

	@Override
	public ResponseBean getActiveFloors() throws CheckListAppException {
		try {
			return ResponseBean.builder().data(prepareFloorBeansFromFloor(floorDao.getActiveFloors())).status(true)
					.hasError(false).message(MessageConstant.SUCCESS_MESSAGE).build();
		} catch (Exception e) {
			throw new CheckListAppException(CheckListAppException.SERVER_ERROR, MessageConstant.SERVER_ERROR_MESSAGE,
					MessageConstant.QUERY_FETCH_EXCPTION);
		}
	}

	@Override
	public ResponseBean getFloors() throws CheckListAppException {
		try {
			return ResponseBean.builder().data(prepareFloorBeansFromFloor(floorDao.findAll())).status(true)
					.hasError(false).message(MessageConstant.SUCCESS_MESSAGE).build();
		} catch (Exception e) {
			throw new CheckListAppException(CheckListAppException.SERVER_ERROR, MessageConstant.SERVER_ERROR_MESSAGE,
					MessageConstant.QUERY_FETCH_EXCPTION);
		}
	}

	@Override
	public ResponseBean getActiveFloorsBybuildingId(Long buildingId) throws CheckListAppException {
		try {
			if (Objects.isNull(buildingId) || buildingId == 0) {
				return getActiveFloors();
			}
			return ResponseBean.builder()
					.data(prepareFloorBeansFromFloor(floorDao.getActiveFloorsBybuildingId(buildingId))).status(true)
					.hasError(false).message(MessageConstant.SUCCESS_MESSAGE).build();
		} catch (Exception e) {
			throw new CheckListAppException(CheckListAppException.SERVER_ERROR, MessageConstant.SERVER_ERROR_MESSAGE,
					MessageConstant.QUERY_FETCH_EXCPTION);
		}
	}

	@Override
	public ResponseBean getFloorByFloorId(Long floorId) throws CheckListAppException {
		try {
			return ResponseBean.builder().data(prepareFloorBeansFromFloor(floorDao.getFloorByFloorId(floorId)))
					.status(true).hasError(false).message(MessageConstant.SUCCESS_MESSAGE).build();
		} catch (Exception e) {
			throw new CheckListAppException(CheckListAppException.SERVER_ERROR, MessageConstant.SERVER_ERROR_MESSAGE,
					MessageConstant.QUERY_FETCH_EXCPTION);
		}
	}

	private List<FloorBean> prepareFloorBeansFromFloor(List<Floor> allFloors) {
		List<FloorBean> floorBeans = new ArrayList<FloorBean>();
		allFloors.forEach(floor -> {
			FloorBean floorBean = new FloorBean();
			BeanUtils.copyProperties(floor, floorBean);
			floorBean.setProjectId(floor.getProject().getProjectId());
			floorBean.setBuildingId(floor.getBuilding().getBuildingId());
			ProjectBean projectBean = new ProjectBean();
			BeanUtils.copyProperties(floor.getProject(), projectBean);
			floorBean.setProject(projectBean);
			BuildingBean buildingBean = new BuildingBean();
			BeanUtils.copyProperties(floor.getBuilding(), buildingBean);
			floorBean.setBuilding(buildingBean);
			floorBeans.add(floorBean);
		});
		return floorBeans;
	}
}
