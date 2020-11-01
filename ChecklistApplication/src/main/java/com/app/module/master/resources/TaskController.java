package com.app.module.master.resources;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.app.beans.TaskBean;
import com.app.beans.ResponseBean;
import com.app.exception.CheckListAppException;
import com.app.module.master.service.ITaskService;

import io.swagger.annotations.ApiOperation;

/**
 * @author Piyush.Thakre
 * @since 13 sep 2020
 *
 */
@RestController
@CrossOrigin
@RequestMapping("/task")
public class TaskController {

	@Autowired
	ITaskService taskService;

	@ApiOperation(value = "save task after validation", response = ResponseEntity.class)
	@PostMapping(value = "/insertTask", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Object> insertTask(@Valid @RequestBody TaskBean taskBean) throws CheckListAppException {
		ResponseBean responseBean = taskService.insertOrUpdateTask(taskBean);
		return new ResponseEntity<Object>(responseBean, HttpStatus.OK);
	}

	@ApiOperation(value = "update task after validation", response = ResponseEntity.class)
	@PutMapping(value = "/updateTask", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Object> updateTask(@Valid @RequestBody TaskBean taskBean) throws CheckListAppException {
		ResponseBean responseBean = taskService.insertOrUpdateTask(taskBean);
		return new ResponseEntity<Object>(responseBean, HttpStatus.OK);
	}

	@GetMapping(value = "/getActiveTasks")
	public ResponseEntity<Object> getActiveTasks() throws CheckListAppException {
		ResponseBean responseBean = taskService.getActiveTasks();
		return new ResponseEntity<Object>(responseBean, HttpStatus.OK);
	}

	@GetMapping(value = "/getTasks")
	public ResponseEntity<Object> getTasks() throws CheckListAppException {
		ResponseBean responseBean = taskService.getTasks();
		return new ResponseEntity<Object>(responseBean, HttpStatus.OK);
	}
}
