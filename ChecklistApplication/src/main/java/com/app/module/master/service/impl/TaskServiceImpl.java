package com.app.module.master.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.app.beans.TaskBean;
import com.app.beans.ResponseBean;
import com.app.constant.MessageConstant;
import com.app.entities.Task;
import com.app.exception.CheckListAppException;
import com.app.module.master.repository.ITaskDao;
import com.app.module.master.service.ITaskService;

/**
 * @author Piyush.Thakre
 * @since 13 sep 2020
 *
 */
@Service
public class TaskServiceImpl implements ITaskService {

	@Autowired
	ITaskDao taskDao;

	@Override
	public ResponseBean insertOrUpdateTask(TaskBean taskBean) throws CheckListAppException {
		Task task = new Task();
		BeanUtils.copyProperties(taskBean, task);
		taskDao.save(task);
		return ResponseBean.builder().message(MessageConstant.DATA_SAVE_SUCCESS)
				.messageDescription(MessageConstant.TASK_SAVE_SUCCESS_MESSAGE).status(true)
				.satusCode(HttpStatus.CREATED.value()).hasError(false).build();
	}

	@Override
	public ResponseBean getActiveTasks() throws CheckListAppException {
		try {
			return ResponseBean.builder().data(prepareTasksBeanFromTasks(taskDao.getAllActiveTasks())).status(true)
					.hasError(false).message(MessageConstant.SUCCESS_MESSAGE).build();
		} catch (Exception e) {
			throw new CheckListAppException(CheckListAppException.SERVER_ERROR, MessageConstant.SERVER_ERROR_MESSAGE,
					MessageConstant.QUERY_FETCH_EXCPTION);
		}
	}

	@Override
	public ResponseBean getTasks() throws CheckListAppException {
		try {
			return ResponseBean.builder().data(prepareTasksBeanFromTasks(taskDao.findAll())).status(true)
					.hasError(false).message(MessageConstant.SUCCESS_MESSAGE).build();
		} catch (Exception e) {
			throw new CheckListAppException(CheckListAppException.SERVER_ERROR, MessageConstant.SERVER_ERROR_MESSAGE,
					MessageConstant.QUERY_FETCH_EXCPTION);
		}
	}

	private List<TaskBean> prepareTasksBeanFromTasks(List<Task> allTasks) {
		List<TaskBean> taskBeans = new ArrayList<TaskBean>();
		allTasks.forEach(task -> {
			TaskBean taskBean = new TaskBean();
			BeanUtils.copyProperties(task, taskBean);
			taskBeans.add(taskBean);
		});
		return taskBeans;
	}
}
