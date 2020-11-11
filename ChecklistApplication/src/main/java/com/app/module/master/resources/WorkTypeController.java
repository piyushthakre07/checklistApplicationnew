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

import com.app.beans.WorkTypeBean;
import com.app.beans.ResponseBean;
import com.app.exception.CheckListAppException;
import com.app.module.master.service.IWorkTypeService;

import io.swagger.annotations.ApiOperation;

/**
 * @author Piyush.Thakre
 * @since 13 sep 2020
 *
 */
@RestController
@CrossOrigin
@RequestMapping("/workType")
public class WorkTypeController {

	@Autowired
	IWorkTypeService workTypeService;

	@ApiOperation(value = "save workType after validation", response = ResponseEntity.class)
	@PostMapping(value = "/insertWorkType", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Object> insertWorkType(@Valid @RequestBody WorkTypeBean workTypeBean)
			throws CheckListAppException {
		ResponseBean responseBean = workTypeService.insertOrUpdateWorkType(workTypeBean);
		return new ResponseEntity<Object>(responseBean, HttpStatus.OK);
	}

	@ApiOperation(value = "update workType after validation", response = ResponseEntity.class)
	@PutMapping(value = "/updateWorkType", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Object> updateWorkType(@Valid @RequestBody WorkTypeBean workTypeBean)
			throws CheckListAppException {
		ResponseBean responseBean = workTypeService.insertOrUpdateWorkType(workTypeBean);
		return new ResponseEntity<Object>(responseBean, HttpStatus.OK);
	}

	@GetMapping(value = "/getActiveWorkTypes")
	public ResponseEntity<Object> getActiveWorkTypes() throws CheckListAppException {
		ResponseBean responseBean = workTypeService.getActiveWorkTypes();
		return new ResponseEntity<Object>(responseBean, HttpStatus.OK);
	}

	@GetMapping(value = "/getWorkTypes")
	public ResponseEntity<Object> getWorkTypes() throws CheckListAppException {
		ResponseBean responseBean = workTypeService.getWorkTypes();
		return new ResponseEntity<Object>(responseBean, HttpStatus.OK);
	}
	
	@GetMapping(value = "/getWorkTypeByWorkTypeId/{workTypeId}")
	public ResponseEntity<Object> getWorkTypeByWorkTypeId(@PathVariable("workTypeId") Long workTypeId)
			throws CheckListAppException {
		return new ResponseEntity<Object>(workTypeService.getWorkTypeByWorkTypeId(workTypeId), HttpStatus.OK);
	}
}
