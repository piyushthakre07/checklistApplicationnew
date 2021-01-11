package com.app.module.master.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.app.beans.ProjectBean;
import com.app.beans.ResponseBean;
import com.app.beans.UserLoginRequestScopeBean;
import com.app.constant.GenericConstant;
import com.app.constant.MessageConstant;
import com.app.entities.Project;
import com.app.exception.CheckListAppException;
import com.app.module.master.repository.IAssignFlatToOwnerDao;
import com.app.module.master.repository.IProjectDao;
import com.app.module.master.service.IProjectService;
import com.app.module.master.validation.IProjectValidation;

/**
 * @author Piyush.Thakre
 * @since 13 sep 2020
 *
 */
@Service
public class ProjectServiceImpl implements IProjectService {

	@Autowired
	IProjectValidation projectValidation;

	@Autowired
	IProjectDao projectDao;

	@Autowired
	UserLoginRequestScopeBean userLoginRequestScopeBean;

	@Autowired
	IAssignFlatToOwnerDao assignFlatToOwnerDao;

	@Override
	public ResponseBean insertOrUpdateProject(ProjectBean projectBean) throws CheckListAppException {
		projectValidation.checkDuplicateProject(projectBean);
		Project project = new Project();
		BeanUtils.copyProperties(projectBean, project);
		projectDao.save(project);
		return ResponseBean.builder().message(MessageConstant.DATA_SAVE_SUCCESS)
				.messageDescription(MessageConstant.PROJECT_SAVE_SUCCESS_MESSAGE).status(true)
				.satusCode(HttpStatus.CREATED.value()).hasError(false).build();
	}

	@Override
	public ResponseBean getActiveProjects() throws CheckListAppException {
		try {
			if (userLoginRequestScopeBean != null && userLoginRequestScopeBean.getUserType() != null
					&& userLoginRequestScopeBean.getUserType().equals(GenericConstant.OWNER)) {
				return ResponseBean.builder()
						.data(prepareProjectsBeanFromProjectsForOwners()).status(true)
						.hasError(false).message(MessageConstant.SUCCESS_MESSAGE).build();
			}
			return ResponseBean.builder().data(prepareProjectsBeanFromProjects(projectDao.getAllActiveProjects()))
					.status(true).hasError(false).message(MessageConstant.SUCCESS_MESSAGE).build();
		} catch (Exception e) {
			throw new CheckListAppException(CheckListAppException.SERVER_ERROR, MessageConstant.SERVER_ERROR_MESSAGE,
					MessageConstant.QUERY_FETCH_EXCPTION);
		}
	}

	@Override
	public ResponseBean getProjects() throws CheckListAppException {
		try {
			return ResponseBean.builder().data(prepareProjectsBeanFromProjects(projectDao.findAll())).status(true)
					.hasError(false).message(MessageConstant.SUCCESS_MESSAGE).build();
		} catch (Exception e) {
			throw new CheckListAppException(CheckListAppException.SERVER_ERROR, MessageConstant.SERVER_ERROR_MESSAGE,
					MessageConstant.QUERY_FETCH_EXCPTION);
		}
	}

	@Override
	public ResponseBean getProjectByProjectId(Long projectId) throws CheckListAppException {
		try {
			return ResponseBean.builder()
					.data(prepareProjectsBeanFromProjects(projectDao.getProjectByProjectId(projectId))).status(true)
					.hasError(false).message(MessageConstant.SUCCESS_MESSAGE).build();
		} catch (Exception e) {
			throw new CheckListAppException(CheckListAppException.SERVER_ERROR, MessageConstant.SERVER_ERROR_MESSAGE,
					MessageConstant.QUERY_FETCH_EXCPTION);
		}
	}

	private List<ProjectBean> prepareProjectsBeanFromProjects(List<Project> allProjects) {
		List<ProjectBean> projectBeans = new ArrayList<ProjectBean>();
		allProjects.forEach(project -> {
			ProjectBean projectBean = new ProjectBean();
			BeanUtils.copyProperties(project, projectBean);
			projectBeans.add(projectBean);
		});
		return projectBeans;
	}
	
	private List<ProjectBean> prepareProjectsBeanFromProjectsForOwners() {
		List<Long> projectIds = assignFlatToOwnerDao
				.getFlatByOwnerId(userLoginRequestScopeBean.getOwner().getOwnerId());
		if (projectIds != null && !projectIds.isEmpty())
			return prepareProjectsBeanFromProjects(projectDao.getProjectByProjectIdList(projectIds));
		else
			return null;
	}
}
