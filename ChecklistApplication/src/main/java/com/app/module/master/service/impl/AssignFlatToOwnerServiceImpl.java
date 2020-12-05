package com.app.module.master.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.app.beans.AssignFlatToOwnerBean;
import com.app.beans.AssignFlatToOwnerResponseBean;
import com.app.beans.BuildingBean;
import com.app.beans.FlatBean;
import com.app.beans.FloorBean;
import com.app.beans.OwnerBean;
import com.app.beans.ProjectBean;
import com.app.beans.ResponseBean;
import com.app.constant.MessageConstant;
import com.app.entities.AssignFlatToOwner;
import com.app.entities.Building;
import com.app.entities.Flat;
import com.app.entities.Floor;
import com.app.entities.Owner;
import com.app.entities.Project;
import com.app.exception.CheckListAppException;
import com.app.module.master.repository.IAssignFlatToOwnerDao;
import com.app.module.master.service.IAssignFlatToOwnerService;

/**
 * @author Piyush.Thakre
 *
 */
@Service
public class AssignFlatToOwnerServiceImpl implements IAssignFlatToOwnerService {

	/*
	 * @Autowired IAssignFlatToOwnerValidation assignFlatToOwnerValidation;
	 */

	@Autowired
	IAssignFlatToOwnerDao assignFlatToOwnerDao;

	@Override
	public ResponseBean insertOrUpdateAssignFlatToOwner(AssignFlatToOwnerBean assignFlatToOwnerBean)
			throws CheckListAppException {
		// assignFlatToOwnerValidation.checkDuplicateAssignFlatToOwner(assignFlatToOwnerBean);
		AssignFlatToOwner assignFlatToOwner = new AssignFlatToOwner();
		BeanUtils.copyProperties(assignFlatToOwnerBean, assignFlatToOwner);
		Project project = new Project();
		project.setProjectId(assignFlatToOwnerBean.getProjectId());
		assignFlatToOwner.setProject(project);
		Building building = new Building();
		building.setBuildingId(assignFlatToOwnerBean.getBuildingId());
		assignFlatToOwner.setBuilding(building);
		Floor floor = new Floor();
		if (assignFlatToOwnerBean.getFloorId() != null) {
			floor.setFloorId(assignFlatToOwnerBean.getFloorId());
			assignFlatToOwner.setFloor(floor);
		}
		Flat flat = new Flat();
		flat.setFlatId(assignFlatToOwnerBean.getFlatId());
		assignFlatToOwner.setFlat(flat);
		Owner owner = new Owner();
		owner.setOwnerId(assignFlatToOwnerBean.getOwnerId());
		assignFlatToOwner.setOwner(owner);
		assignFlatToOwnerDao.save(assignFlatToOwner);
		return ResponseBean.builder().message(MessageConstant.DATA_SAVE_SUCCESS)
				.messageDescription(MessageConstant.ASSIGN_FLAT_TO_OWNER).status(true)
				.satusCode(HttpStatus.CREATED.value()).hasError(false).build();
	}

	@Override
	public ResponseBean getActiveAssignFlatToOwners() throws CheckListAppException {
		try {
			return ResponseBean.builder()
					.data(prepareAssignFlatToOwnerBeansFromAssignFlatToOwner(
							assignFlatToOwnerDao.getActiveAssignFlatToOwners()))
					.status(true).hasError(false).message(MessageConstant.SUCCESS_MESSAGE).build();
		} catch (Exception e) {
			throw new CheckListAppException(CheckListAppException.SERVER_ERROR, MessageConstant.SERVER_ERROR_MESSAGE,
					MessageConstant.QUERY_FETCH_EXCPTION);
		}
	}

	@Override
	public ResponseBean getAssignFlatToOwners() throws CheckListAppException {
		try {
			return ResponseBean.builder()
					.data(prepareAssignFlatToOwnerBeansFromAssignFlatToOwner(assignFlatToOwnerDao.findAll()))
					.status(true).hasError(false).message(MessageConstant.SUCCESS_MESSAGE).build();
		} catch (Exception e) {
			throw new CheckListAppException(CheckListAppException.SERVER_ERROR, MessageConstant.SERVER_ERROR_MESSAGE,
					MessageConstant.QUERY_FETCH_EXCPTION);
		}
	}
	
	@Override
	public ResponseBean getAssignFlatToOwnerByAssignFlatToOwnerId(Long assignFlatToOwnerId) throws CheckListAppException {
		try {
			return ResponseBean.builder()
					.data(prepareAssignFlatToOwnerBeansFromAssignFlatToOwner(assignFlatToOwnerDao.getAssignFlatToOwnerByAssignFlatToOwnerId(assignFlatToOwnerId))).status(true)
					.hasError(false).message(MessageConstant.SUCCESS_MESSAGE).build();
		} catch (Exception e) {
			throw new CheckListAppException(CheckListAppException.SERVER_ERROR, MessageConstant.SERVER_ERROR_MESSAGE,
					MessageConstant.QUERY_FETCH_EXCPTION);
		}
	}
	
	@Override
	public ResponseBean getOwnersByFlatId(Long flatId) throws CheckListAppException {
		try {
			return ResponseBean.builder().data(assignFlatToOwnerDao.getOwnersByFlatId(flatId))
					.status(true).hasError(false).message(MessageConstant.SUCCESS_MESSAGE).build();
		} catch (Exception e) {
			throw new CheckListAppException(CheckListAppException.SERVER_ERROR, MessageConstant.SERVER_ERROR_MESSAGE,
					MessageConstant.QUERY_FETCH_EXCPTION);
		}
	}
	
	private List<AssignFlatToOwnerResponseBean> prepareAssignFlatToOwnerBeansFromAssignFlatToOwner(
			List<AssignFlatToOwner> allAssignFlatToOwners) {
		List<AssignFlatToOwnerResponseBean> assignFlatToOwnerBeans = new ArrayList<AssignFlatToOwnerResponseBean>();
		allAssignFlatToOwners.forEach(assignFlatToOwner -> {
			AssignFlatToOwnerResponseBean assignFlatToOwnerResponseBean = new AssignFlatToOwnerResponseBean();
			BeanUtils.copyProperties(assignFlatToOwner, assignFlatToOwnerResponseBean);
			ProjectBean projectBean = new ProjectBean();
			BeanUtils.copyProperties(assignFlatToOwner.getProject(), projectBean);
			assignFlatToOwnerResponseBean.setProject(projectBean);
			BuildingBean buildingBean = new BuildingBean();
			BeanUtils.copyProperties(assignFlatToOwner.getBuilding(), buildingBean);
			assignFlatToOwnerResponseBean.setBuilding(buildingBean);
			FloorBean floorBean = new FloorBean();
			if (assignFlatToOwner.getFloor() != null) {
				BeanUtils.copyProperties(assignFlatToOwner.getFloor(), floorBean);
				assignFlatToOwnerResponseBean.setFloor(floorBean);
			}
			FlatBean flatBean = new FlatBean();
			BeanUtils.copyProperties(assignFlatToOwner.getFlat(), flatBean);
			assignFlatToOwnerResponseBean.setFlat(flatBean);
			OwnerBean ownerBean = new OwnerBean();
			BeanUtils.copyProperties(assignFlatToOwner.getOwner(), ownerBean);
			assignFlatToOwnerResponseBean.setOwner(ownerBean);
			assignFlatToOwnerBeans.add(assignFlatToOwnerResponseBean);
		});
		return assignFlatToOwnerBeans;
	}
	
}
