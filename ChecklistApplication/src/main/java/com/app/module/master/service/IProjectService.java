package com.app.module.master.service;

import com.app.beans.ProjectBean;
import com.app.beans.ResponseBean;
import com.app.exception.CheckListAppException;

/**
 * @author Piyush.Thakre
 *
 */
public interface IProjectService {

	ResponseBean insertOrUpdateProject(ProjectBean projectBean) throws CheckListAppException;

	ResponseBean getActiveProjects() throws CheckListAppException;

	ResponseBean getProjects() throws CheckListAppException;
	
	ResponseBean getProjectByProjectId(Long projectId) throws CheckListAppException;

}
