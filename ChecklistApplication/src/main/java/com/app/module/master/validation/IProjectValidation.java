package com.app.module.master.validation;

import com.app.beans.ProjectBean;
import com.app.exception.CheckListAppException;

/**
 * @author Piyush.Thakre
 *
 */
public interface IProjectValidation {

	void fieldValidationProjectRequest(ProjectBean projectBean) throws CheckListAppException;

	void checkDuplicateProject(ProjectBean projectBean) throws CheckListAppException;

}
