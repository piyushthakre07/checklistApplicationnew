package com.app.module.master.resources;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.app.beans.ProjectBean;
import com.app.exception.CheckListAppException;
import com.app.module.master.service.IProjectService;

import io.swagger.annotations.ApiOperation;

/**
 * @author Piyush.Thakre
 * @since 13 sep 2020
 *
 */
@RestController
@CrossOrigin
@RequestMapping("/project")
public class ProjectController {

	@Autowired
	IProjectService projectService;

	@ApiOperation(value = "save project after validation", response = ResponseEntity.class)
	@PostMapping(value = "/insertProject", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Object> insertProject(@Valid @RequestBody ProjectBean projectBean)
			throws CheckListAppException {
		return new ResponseEntity<Object>(projectService.insertOrUpdateProject(projectBean), HttpStatus.OK);
	}

	@ApiOperation(value = "update project after validation", response = ResponseEntity.class)
	@PutMapping(value = "/updateProject", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Object> updateProject(@Valid @RequestBody ProjectBean projectBean)
			throws CheckListAppException {
		return new ResponseEntity<Object>(projectService.insertOrUpdateProject(projectBean), HttpStatus.OK);
	}

	@GetMapping(value = "/getActiveProjects")
	public ResponseEntity<Object> getActiveProjects() throws CheckListAppException {
		return new ResponseEntity<Object>(projectService.getActiveProjects(), HttpStatus.OK);
	}

	@GetMapping(value = "/getProjects")
	public ResponseEntity<Object> getProjects() throws CheckListAppException {
		return new ResponseEntity<Object>(projectService.getProjects(), HttpStatus.OK);
	}

	@GetMapping(value = "/getProjectByProjectId/{projectId}")
	public ResponseEntity<Object> getProjectByProjectId(@PathVariable("projectId") Long projectId)
			throws CheckListAppException {
		return new ResponseEntity<Object>(projectService.getProjectByProjectId(projectId), HttpStatus.OK);
	}

}
