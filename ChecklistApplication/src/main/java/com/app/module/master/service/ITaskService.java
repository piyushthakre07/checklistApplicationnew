package com.app.module.master.service;

import com.app.beans.TaskBean;
import com.app.beans.ResponseBean;
import com.app.exception.CheckListAppException;

public interface ITaskService {

	ResponseBean insertOrUpdateTask(TaskBean taskBean) throws CheckListAppException;

	ResponseBean getActiveTasks() throws CheckListAppException;

	ResponseBean getTasks() throws CheckListAppException;
}
