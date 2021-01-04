package com.app.module.master.service;

import java.util.List;

import com.app.beans.AssignTaskToFlatBean;
import com.app.beans.ResponseBean;
import com.app.beans.TaskResponseBean;
import com.app.beans.WorkTypeResponseBean;
import com.app.exception.CheckListAppException;

public interface IAssignTaskToFlatService {

	ResponseBean insertOrUpdateAssignTaskToFlat( AssignTaskToFlatBean assignTaskToFlatBean) throws CheckListAppException;

	ResponseBean getActiveAssignTaskToFlats() throws CheckListAppException;

	ResponseBean getAssignTaskToFlats() throws CheckListAppException;

	ResponseBean getTaskByFlatIdAndWorktype(Long flatId, Long workTypeId) throws CheckListAppException;

	ResponseBean getAssignTaskToFlatById(Long assignTaskToFlatId) throws CheckListAppException;

	List<WorkTypeResponseBean> getWorkTypeResponseBean(Long flatId) throws CheckListAppException;

	List<TaskResponseBean> getTaskResponseBeanByFlatIdAndWorktype(Long flatId, Long workTypeId)
			throws CheckListAppException;

}
