package com.app.module.master.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.app.beans.AssignWorkToContractorBean;
import com.app.beans.AssignWorkToContractorResponseBean;
import com.app.beans.BuildingBean;
import com.app.beans.ContractorBean;
import com.app.beans.FlatBean;
import com.app.beans.FloorBean;
import com.app.beans.ProjectBean;
import com.app.beans.ResponseBean;
import com.app.beans.WorkTypeBean;
import com.app.constant.MessageConstant;
import com.app.entities.AssignWorkToContractor;
import com.app.entities.Building;
import com.app.entities.Contractor;
import com.app.entities.Flat;
import com.app.entities.Floor;
import com.app.entities.Project;
import com.app.entities.WorkType;
import com.app.exception.CheckListAppException;
import com.app.module.master.repository.IAssignWorkToContractorDao;
import com.app.module.master.service.IAssignWorkToContractorService;

/**
 * @author Piyush.Thakre
 *
 */
@Service
public class AssignWorkToContractorServiceImpl implements IAssignWorkToContractorService {

	/*
	 * @Autowired IAssignWorkToContractorValidation
	 * assignWorkToContractorValidation;
	 */

	@Autowired
	IAssignWorkToContractorDao assignWorkToContractorDao;

	@Override
	public ResponseBean insertOrUpdateAssignWorkToContractor(AssignWorkToContractorBean assignWorkToContractorBean)
			throws CheckListAppException {
		// assignWorkToContractorValidation.checkDuplicateAssignWorkToContractor(assignWorkToContractorBean);
		AssignWorkToContractor assignWorkToContractor = new AssignWorkToContractor();
		BeanUtils.copyProperties(assignWorkToContractorBean, assignWorkToContractor);
		Project project = new Project();
		project.setProjectId(assignWorkToContractorBean.getProjectId());
		assignWorkToContractor.setProject(project);
		Building building = new Building();
		building.setBuildingId(assignWorkToContractorBean.getBuildingId());
		assignWorkToContractor.setBuilding(building);
		Floor floor = new Floor();
		if (assignWorkToContractorBean.getFloorId() != null) {
			floor.setFloorId(assignWorkToContractorBean.getFloorId());
			assignWorkToContractor.setFloor(floor);
		}
		Flat flat = new Flat();
		flat.setFlatId(assignWorkToContractorBean.getFlatId());
		assignWorkToContractor.setFlat(flat);
		WorkType workType = new WorkType();
		workType.setWorkTypeId(assignWorkToContractorBean.getWorkTypeId());
		assignWorkToContractor.setWorkType(workType);
		Contractor contractor = new Contractor();
		contractor.setContractorId(assignWorkToContractorBean.getContractorId());
		assignWorkToContractor.setContractor(contractor);
		assignWorkToContractorDao.save(assignWorkToContractor);
		return ResponseBean.builder().message(MessageConstant.DATA_SAVE_SUCCESS)
				.messageDescription(MessageConstant.ASSIGN_WORK_TO_CONTRACTOR).status(true)
				.satusCode(HttpStatus.CREATED.value()).hasError(false).build();
	}

	@Override
	public ResponseBean getActiveAssignWorkToContractors() throws CheckListAppException {
		try {
			return ResponseBean.builder()
					.data(prepareAssignWorkToContractorBeansFromAssignWorkToContractor(
							assignWorkToContractorDao.getActiveAssignWorkToContractors()))
					.status(true).hasError(false).message(MessageConstant.SUCCESS_MESSAGE).build();
		} catch (Exception e) {
			throw new CheckListAppException(CheckListAppException.SERVER_ERROR, MessageConstant.SERVER_ERROR_MESSAGE,
					MessageConstant.QUERY_FETCH_EXCPTION);
		}
	}

	@Override
	public ResponseBean getAssignWorkToContractors() throws CheckListAppException {
		try {
			return ResponseBean.builder()
					.data(prepareAssignWorkToContractorBeansFromAssignWorkToContractor(
							assignWorkToContractorDao.findAll()))
					.status(true).hasError(false).message(MessageConstant.SUCCESS_MESSAGE).build();
		} catch (Exception e) {
			throw new CheckListAppException(CheckListAppException.SERVER_ERROR, MessageConstant.SERVER_ERROR_MESSAGE,
					MessageConstant.QUERY_FETCH_EXCPTION);
		}
	}
	
	@Override
	public ResponseBean getAssignWorkToContractorById(Long assignWorkToContractorId) throws CheckListAppException {
		try {
			return ResponseBean.builder()
					.data(prepareAssignWorkToContractorBeansFromAssignWorkToContractor(assignWorkToContractorDao.getAssignWorkToContractorById(assignWorkToContractorId))).status(true)
					.hasError(false).message(MessageConstant.SUCCESS_MESSAGE).build();
		} catch (Exception e) {
			throw new CheckListAppException(CheckListAppException.SERVER_ERROR, MessageConstant.SERVER_ERROR_MESSAGE,
					MessageConstant.QUERY_FETCH_EXCPTION);
		}
	}
	
	@Override
	public List<AssignWorkToContractorResponseBean> getAssignWorkToContractorByFlatIdNWorkType(Long flatId, Long workTypeId) throws CheckListAppException {
		try {
			return prepareAssignWorkToContractorBeansFromAssignWorkToContractor(
							assignWorkToContractorDao.getAssignWorkToContractorByFlatIdNWorkType(flatId, workTypeId));
		} catch (Exception e) {
			throw new CheckListAppException(CheckListAppException.SERVER_ERROR, MessageConstant.SERVER_ERROR_MESSAGE,
					MessageConstant.QUERY_FETCH_EXCPTION);
		}
	}
	
	

	private List<AssignWorkToContractorResponseBean> prepareAssignWorkToContractorBeansFromAssignWorkToContractor(
			List<AssignWorkToContractor> allAssignWorkToContractors) {
		List<AssignWorkToContractorResponseBean> assignWorkToContractorBeans = new ArrayList<AssignWorkToContractorResponseBean>();
		allAssignWorkToContractors.forEach(assignWorkToContractor -> {
			AssignWorkToContractorResponseBean assignWorkToContractorResponseBean = new AssignWorkToContractorResponseBean();
			BeanUtils.copyProperties(assignWorkToContractor, assignWorkToContractorResponseBean);
			ProjectBean projectBean = new ProjectBean();
			BeanUtils.copyProperties(assignWorkToContractor.getProject(), projectBean);
			assignWorkToContractorResponseBean.setProject(projectBean);
			BuildingBean buildingBean = new BuildingBean();
			BeanUtils.copyProperties(assignWorkToContractor.getBuilding(), buildingBean);
			assignWorkToContractorResponseBean.setBuilding(buildingBean);
			FloorBean floorBean = new FloorBean();
			if (assignWorkToContractor.getFloor() != null) {
				BeanUtils.copyProperties(assignWorkToContractor.getFloor(), floorBean);
				assignWorkToContractorResponseBean.setFloor(floorBean);
			}
			FlatBean flatBean = new FlatBean();
			BeanUtils.copyProperties(assignWorkToContractor.getFlat(), flatBean);
			assignWorkToContractorResponseBean.setFlat(flatBean);
			WorkTypeBean workTypeBean = new WorkTypeBean();
			BeanUtils.copyProperties(assignWorkToContractor.getWorkType(), workTypeBean);
			assignWorkToContractorResponseBean.setWorkType(workTypeBean);
			ContractorBean contractorBean = new ContractorBean();
			BeanUtils.copyProperties(assignWorkToContractor.getContractor(), contractorBean);
			assignWorkToContractorResponseBean.setContractor(contractorBean);
			assignWorkToContractorBeans.add(assignWorkToContractorResponseBean);
		});
		return assignWorkToContractorBeans;
	}
}
