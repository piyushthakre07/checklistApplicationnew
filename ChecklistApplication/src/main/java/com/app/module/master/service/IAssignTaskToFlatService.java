package com.app.module.master.service;

import com.app.beans.AssignTaskToFlatBean;
import com.app.beans.ResponseBean;
import com.app.exception.CheckListAppException;

public interface IAssignTaskToFlatService {

	ResponseBean insertOrUpdateAssignTaskToFlat( AssignTaskToFlatBean assignTaskToFlatBean) throws CheckListAppException;

	ResponseBean getActiveAssignTaskToFlats() throws CheckListAppException;

	ResponseBean getAssignTaskToFlats() throws CheckListAppException;

}
