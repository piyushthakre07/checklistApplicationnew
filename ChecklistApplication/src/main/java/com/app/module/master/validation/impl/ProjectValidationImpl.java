package com.app.module.master.validation.impl;

import org.springframework.stereotype.Service;

import com.app.beans.ProjectBean;
import com.app.exception.CheckListAppException;
import com.app.module.master.validation.IProjectValidation;

/**
 * @author Piyush.Thakre
 * @since 13 sep 2020
 *
 */
@Service
public class ProjectValidationImpl implements IProjectValidation {

	@Override
	public void fieldValidationProjectRequest(ProjectBean projectBean) throws CheckListAppException {
		/*
		 * if (Objects.isNull(projectBean) ||
		 * Objects.isNull(projectBean.getProjectName())) { throw new
		 * CheckListAppException(CheckListAppException.BAD_REQUEST,
		 * MessageConstant.INVALID_REQUEST, MessageConstant.INVALID_REQUEST); }
		 */}

	@Override
	public void checkDuplicateProject(ProjectBean projectBean)  throws CheckListAppException{
		// TODO Auto-generated method stub

	}

}
