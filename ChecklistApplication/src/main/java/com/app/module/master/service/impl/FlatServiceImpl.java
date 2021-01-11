package com.app.module.master.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.app.beans.BuildingBean;
import com.app.beans.FlatBean;
import com.app.beans.FlatRequestBean;
import com.app.beans.FlatTypeBean;
import com.app.beans.FloorBean;
import com.app.beans.ProjectBean;
import com.app.beans.ResponseBean;
import com.app.beans.UserLoginRequestScopeBean;
import com.app.constant.GenericConstant;
import com.app.constant.MessageConstant;
import com.app.entities.Building;
import com.app.entities.Flat;
import com.app.entities.FlatType;
import com.app.entities.Floor;
import com.app.entities.Project;
import com.app.exception.CheckListAppException;
import com.app.module.master.repository.IAssignFlatToOwnerDao;
import com.app.module.master.repository.IFlatDao;
import com.app.module.master.service.IFlatService;

/**
 * @author Piyush.Thakre
 *
 */
@Service
public class FlatServiceImpl implements IFlatService {

	/*
	 * @Autowired IFlatValidation flatValidation;
	 */

	@Autowired
	IFlatDao flatDao;
	
	@Autowired
	UserLoginRequestScopeBean userLoginRequestScopeBean;

	@Autowired
	IAssignFlatToOwnerDao assignFlatToOwnerDao;

	@Override
	public ResponseBean insertOrUpdateFlat(FlatBean flatBean) throws CheckListAppException {
		// flatValidation.checkDuplicateFlat(flatBean);
		Flat flat = new Flat();
		BeanUtils.copyProperties(flatBean, flat);
		Project project = new Project();
		project.setProjectId(flatBean.getProjectId());
		flat.setProject(project);
		Building building = new Building();
		building.setBuildingId(flatBean.getBuildingId());
		flat.setBuilding(building);
		Floor floor = new Floor();
		floor.setFloorId(flatBean.getFloorId());
		flat.setFloor(floor);
		FlatType flatType = new FlatType();
		flatType.setFlatTypeId(flatBean.getFlatTypeId());
		flat.setFlatType(flatType);
		flatDao.save(flat);
		return ResponseBean.builder().message(MessageConstant.DATA_SAVE_SUCCESS)
				.messageDescription(MessageConstant.FLAT_SAVE_SUCCESS_MESSAGE).status(true)
				.satusCode(HttpStatus.CREATED.value()).hasError(false).build();
	}
  
	@Override
	public ResponseBean insertMultipleFlat(FlatRequestBean flatRequestBean) throws CheckListAppException {
		// flatValidation.checkDuplicateFlat(flatBean);
		try {
			flatRequestBean.getFlatBeanList().forEach(flatBean -> {
				Flat flat = new Flat();
				BeanUtils.copyProperties(flatBean, flat);
				Project project = new Project();
				project.setProjectId(flatRequestBean.getProjectId());
				flat.setProject(project);
				Building building = new Building();
				building.setBuildingId(flatBean.getBuildingId());
				flat.setBuilding(building);
				FlatType flatType = new FlatType();
				flatType.setFlatTypeId(flatBean.getFlatTypeId());
				flat.setFlatType(flatType);
				Floor floor = new Floor();
				floor.setFloorId(flatBean.getFloorId());
				flat.setFloor(floor);
				flatDao.save(flat);
			});
			return ResponseBean.builder().message(MessageConstant.DATA_SAVE_SUCCESS)
					.messageDescription(MessageConstant.FLAT_SAVE_SUCCESS_MESSAGE).status(true)
					.satusCode(HttpStatus.CREATED.value()).hasError(false).build();
		} catch (Exception e) {
			throw new CheckListAppException(CheckListAppException.SERVER_ERROR, MessageConstant.SERVER_ERROR_MESSAGE,
					MessageConstant.DATA_SAVE_EXCPTION);
		}
	}

	@Override
	public ResponseBean getActiveFlats() throws CheckListAppException {
		try {
			if (userLoginRequestScopeBean != null && userLoginRequestScopeBean.getUserType() != null
					&& userLoginRequestScopeBean.getUserType().equals(GenericConstant.OWNER)) {
				return ResponseBean.builder().data(prepareFlatBeanForOwners()).status(true)
						.hasError(false).message(MessageConstant.SUCCESS_MESSAGE).build();
				
			}
			return ResponseBean.builder().data(prepareFlatBeansFromFlat(flatDao.getActiveFlats())).status(true)
					.hasError(false).message(MessageConstant.SUCCESS_MESSAGE).build();
		} catch (Exception e) {
			throw new CheckListAppException(CheckListAppException.SERVER_ERROR, MessageConstant.SERVER_ERROR_MESSAGE,
					MessageConstant.QUERY_FETCH_EXCPTION);
		}
	}

	@Override
	public ResponseBean getActiveFlatsByFloorId(Long floorId) throws CheckListAppException {
		try {
			if (Objects.isNull(floorId) || floorId == 0)
				return getActiveFlats();

			if (userLoginRequestScopeBean != null && userLoginRequestScopeBean.getUserType() != null
					&& userLoginRequestScopeBean.getUserType().equals(GenericConstant.OWNER)) {
				return ResponseBean.builder().data(prepareFlatBeanForOwners(floorId))
						.status(true).hasError(false).message(MessageConstant.SUCCESS_MESSAGE).build();
			}
			return ResponseBean.builder().data(prepareFlatBeansFromFlat(flatDao.getActiveFlatsByFloorId(floorId)))
					.status(true).hasError(false).message(MessageConstant.SUCCESS_MESSAGE).build();
		} catch (Exception e) {
			throw new CheckListAppException(CheckListAppException.SERVER_ERROR, MessageConstant.SERVER_ERROR_MESSAGE,
					MessageConstant.QUERY_FETCH_EXCPTION);
		}
	}

	@Override
	public ResponseBean getActiveFlatsByBuildingId(Long buildingId) throws CheckListAppException {
		try {
			if (Objects.isNull(buildingId) || buildingId == 0)
				return getActiveFlats();

			if (userLoginRequestScopeBean != null && userLoginRequestScopeBean.getUserType() != null
					&& userLoginRequestScopeBean.getUserType().equals(GenericConstant.OWNER)) {
				return ResponseBean.builder().data(prepareFlatBeanForOwnersByBuildingId(buildingId))
						.status(true).hasError(false).message(MessageConstant.SUCCESS_MESSAGE).build();
			}
			
			return ResponseBean.builder().data(prepareFlatBeansFromFlat(flatDao.getActiveFlatsByBuildingId(buildingId)))
					.status(true).hasError(false).message(MessageConstant.SUCCESS_MESSAGE).build();
		} catch (Exception e) {
			throw new CheckListAppException(CheckListAppException.SERVER_ERROR, MessageConstant.SERVER_ERROR_MESSAGE,
					MessageConstant.QUERY_FETCH_EXCPTION);
		}
	}

	@Override
	public ResponseBean getFlats() throws CheckListAppException {
		try {
			return ResponseBean.builder().data(prepareFlatBeansFromFlat(flatDao.findAll())).status(true).hasError(false)
					.message(MessageConstant.SUCCESS_MESSAGE).build();
		} catch (Exception e) {
			throw new CheckListAppException(CheckListAppException.SERVER_ERROR, MessageConstant.SERVER_ERROR_MESSAGE,
					MessageConstant.QUERY_FETCH_EXCPTION);
		}
	}
	
	@Override
	public ResponseBean getFlatByFlatId(Long flatId) throws CheckListAppException {
		try {
			return ResponseBean.builder()
					.data(prepareFlatBeansFromFlat(flatDao.getFlatByFlatId(flatId)))
					.status(true).hasError(false).message(MessageConstant.SUCCESS_MESSAGE).build();
		} catch (Exception e) {
			throw new CheckListAppException(CheckListAppException.SERVER_ERROR, MessageConstant.SERVER_ERROR_MESSAGE,
					MessageConstant.QUERY_FETCH_EXCPTION);
		}
	}
	private List<FlatBean> prepareFlatBeansFromFlat(List<Flat> allFlats) {
		List<FlatBean> flatBeans = new ArrayList<FlatBean>();
		allFlats.forEach(flat -> {
			FlatBean flatBean = new FlatBean();
			BeanUtils.copyProperties(flat, flatBean);
			flatBean.setProjectId(flat.getProject().getProjectId());
			flatBean.setBuildingId(flat.getBuilding().getBuildingId());
			flatBean.setFloorId(flat.getFloor().getFloorId());
			flatBean.setFlatTypeId(flat.getFlatType().getFlatTypeId());
			ProjectBean projectBean = new ProjectBean();
			BeanUtils.copyProperties(flat.getProject(), projectBean);
			flatBean.setProject(projectBean);
			BuildingBean buildingBean = new BuildingBean();
			BeanUtils.copyProperties(flat.getBuilding(), buildingBean);
			flatBean.setBuilding(buildingBean);
			FloorBean floorBean = new FloorBean();
			BeanUtils.copyProperties(flat.getFloor(), floorBean);
			flatBean.setFloor(floorBean);
			FlatTypeBean flatType = new FlatTypeBean();
			BeanUtils.copyProperties(flat.getFlatType(), flatType);
			flatBean.setFlatType(flatType);
			flatBeans.add(flatBean);
		});
		return flatBeans;
	}
	
	
	private List<FlatBean> prepareFlatBeanForOwners() {
		List<Long> flatIds = assignFlatToOwnerDao
				.getFlatIdsByOwnerId(userLoginRequestScopeBean.getOwner().getOwnerId());
		if (flatIds != null && !flatIds.isEmpty())
			return prepareFlatBeansFromFlat(flatDao.getFlatByFlatIdList(flatIds));
		else
			return null;
	}
	
	private List<FlatBean> prepareFlatBeanForOwners(Long floorId) {
		List<Long> flatIds = assignFlatToOwnerDao
				.getFlatIdsByOwnerId(userLoginRequestScopeBean.getOwner().getOwnerId());
		if (flatIds != null && !flatIds.isEmpty())
			return prepareFlatBeansFromFlat(flatDao.getFlatByFlatIdList(flatIds,floorId));
		else
			return null;
	}
	
	private List<FlatBean> prepareFlatBeanForOwnersByBuildingId(Long buildingId) {
		List<Long> flatIds = assignFlatToOwnerDao
				.getFlatIdsByOwnerId(userLoginRequestScopeBean.getOwner().getOwnerId());
		if (flatIds != null && !flatIds.isEmpty())
			return prepareFlatBeansFromFlat(flatDao.prepareFlatBeanForOwnersByBuildingId(flatIds,buildingId));
		else
			return null;
	}
}
