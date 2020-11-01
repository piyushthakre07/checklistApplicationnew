package com.app.module.master.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.app.beans.BuildingBean;
import com.app.beans.BuildingRequestBean;
import com.app.beans.ProjectBean;
import com.app.beans.ResponseBean;
import com.app.constant.MessageConstant;
import com.app.entities.Building;
import com.app.entities.Project;
import com.app.exception.CheckListAppException;
import com.app.module.master.repository.IBuildingDao;
import com.app.module.master.service.IBuildingService;

/**
 * @author Piyush.Thakre
 * @since 13 sep 2020
 *
 */
@Service
public class BuildingServiceImpl implements IBuildingService {

	/*
	 * @Autowired IBuildingValidation buildingValidation;
	 */

	@Autowired
	IBuildingDao buildingDao;

	@Override
	public ResponseBean insertOrUpdateBuilding(BuildingBean buildingBean) throws CheckListAppException {
		// buildingValidation.checkDuplicateBuilding(buildingBean);
		Building building = new Building();
		BeanUtils.copyProperties(buildingBean, building);
		Project project = new Project();
		project.setProjectId(buildingBean.getProjectId());
		building.setProject(project);
		buildingDao.save(building);
		return ResponseBean.builder().message(MessageConstant.DATA_SAVE_SUCCESS)
				.messageDescription(MessageConstant.BUILDING_SAVE_SUCCESS_MESSAGE).status(true)
				.satusCode(HttpStatus.CREATED.value()).hasError(false).build();
	}

	@Override
	public ResponseBean insertMultipleBuilding(BuildingRequestBean buildingRequestBean) throws CheckListAppException {
		// buildingValidation.checkDuplicateBuilding(buildingBean);
		try {
			buildingRequestBean.getBuildingBeanList().forEach(buildingBean -> {
				Building building = new Building();
				BeanUtils.copyProperties(buildingBean, building);
				Project project = new Project();
				project.setProjectId(buildingRequestBean.getProjectId());
				building.setProject(project);
				buildingDao.save(building);
			});
			return ResponseBean.builder().message(MessageConstant.DATA_SAVE_SUCCESS)
					.messageDescription(MessageConstant.BUILDING_SAVE_SUCCESS_MESSAGE).status(true)
					.satusCode(HttpStatus.CREATED.value()).hasError(false).build();
		} catch (Exception e) {
			throw new CheckListAppException(CheckListAppException.SERVER_ERROR, MessageConstant.SERVER_ERROR_MESSAGE,
					MessageConstant.DATA_SAVE_EXCPTION);
		}
	}

	@Override
	public ResponseBean getActiveBuildings() throws CheckListAppException {
		try {
			return ResponseBean.builder().data(prepareBuildingBeansFromBuilding(buildingDao.getActiveBuildings()))
					.status(true).hasError(false).message(MessageConstant.SUCCESS_MESSAGE).build();
		} catch (Exception e) {
			throw new CheckListAppException(CheckListAppException.SERVER_ERROR, MessageConstant.SERVER_ERROR_MESSAGE,
					MessageConstant.QUERY_FETCH_EXCPTION);
		}
	}

	@Override
	public ResponseBean getBuildings() throws CheckListAppException {
		try {
			return ResponseBean.builder().data(prepareBuildingBeansFromBuilding(buildingDao.findAll())).status(true)
					.hasError(false).message(MessageConstant.SUCCESS_MESSAGE).build();
		} catch (Exception e) {
			throw new CheckListAppException(CheckListAppException.SERVER_ERROR, MessageConstant.SERVER_ERROR_MESSAGE,
					MessageConstant.QUERY_FETCH_EXCPTION);
		}
	}

	@Override
	public ResponseBean getActiveBuildingsByProjectId(Long projectId) throws CheckListAppException {
		try {
			if (Objects.isNull(projectId) || projectId == 0) {
				return getActiveBuildings();
			}
			return ResponseBean.builder()
					.data(prepareBuildingBeansFromBuilding(buildingDao.getActiveBuildingsByProjectId(projectId)))
					.status(true).hasError(false).message(MessageConstant.SUCCESS_MESSAGE).build();
		} catch (Exception e) {
			throw new CheckListAppException(CheckListAppException.SERVER_ERROR, MessageConstant.SERVER_ERROR_MESSAGE,
					MessageConstant.QUERY_FETCH_EXCPTION);
		}
	}
	
	@Override
	public ResponseBean getBuildingByBuildingId(Long buildingId) throws CheckListAppException {
		try {
			return ResponseBean.builder()
					.data(prepareBuildingBeansFromBuilding(buildingDao.getBuildingByBuildingId(buildingId)))
					.status(true).hasError(false).message(MessageConstant.SUCCESS_MESSAGE).build();
		} catch (Exception e) {
			throw new CheckListAppException(CheckListAppException.SERVER_ERROR, MessageConstant.SERVER_ERROR_MESSAGE,
					MessageConstant.QUERY_FETCH_EXCPTION);
		}
	}

	private List<BuildingBean> prepareBuildingBeansFromBuilding(List<Building> allBuildings) {
		List<BuildingBean> buildingBeans = new ArrayList<BuildingBean>();
		allBuildings.forEach(building -> {
			BuildingBean buildingBean = new BuildingBean();
			BeanUtils.copyProperties(building, buildingBean);
			buildingBean.setProjectId(building.getProject().getProjectId());
			ProjectBean projectBean = new ProjectBean();
			BeanUtils.copyProperties(building.getProject(), projectBean);
			buildingBean.setProject(projectBean);
			buildingBeans.add(buildingBean);
		});
		return buildingBeans;
	}
}
