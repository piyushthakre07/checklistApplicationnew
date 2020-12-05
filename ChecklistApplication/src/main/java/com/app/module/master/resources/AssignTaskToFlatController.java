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

import com.app.beans.AssignTaskToFlatBean;
import com.app.beans.ResponseBean;
import com.app.exception.CheckListAppException;
import com.app.module.master.service.IAssignTaskToFlatService;

import io.swagger.annotations.ApiOperation;

/**
 * @author Piyush.Thakre
 * @since 18 oct 2020
 *
 */
@RestController
@CrossOrigin
@RequestMapping("/assignTaskToFlat")
public class AssignTaskToFlatController {

	@Autowired
	IAssignTaskToFlatService assignTaskToFlatService;

	@ApiOperation(value = "save assignTaskToFlat after validation", response = ResponseEntity.class)
	@PostMapping(value = "/insertAssignTaskToFlat", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Object> insertAssignTaskToFlat(
			@Valid @RequestBody AssignTaskToFlatBean assignTaskToFlatBean) throws CheckListAppException {
		ResponseBean responseBean = assignTaskToFlatService.insertOrUpdateAssignTaskToFlat(assignTaskToFlatBean);
		return new ResponseEntity<Object>(responseBean, HttpStatus.OK);
	}

	@ApiOperation(value = "update assignTaskToFlat after validation", response = ResponseEntity.class)
	@PutMapping(value = "/updateAssignTaskToFlat", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Object> updateAssignTaskToFlat(
			@Valid @RequestBody AssignTaskToFlatBean assignTaskToFlatBean) throws CheckListAppException {
		ResponseBean responseBean = assignTaskToFlatService.insertOrUpdateAssignTaskToFlat(assignTaskToFlatBean);
		return new ResponseEntity<Object>(responseBean, HttpStatus.OK);
	}

	@GetMapping(value = "/getAllActiveAssignTaskToFlat")
	public ResponseEntity<Object> getAllActiveAssignTaskToFlat() throws CheckListAppException {
		ResponseBean responseBean = assignTaskToFlatService.getActiveAssignTaskToFlats();
		return new ResponseEntity<Object>(responseBean, HttpStatus.OK);
	}
	
	@GetMapping(value = "/getTaskByFlatIdAndWorktype/{flatId}/{workTypeId}")
	public ResponseEntity<Object> getTaskByFlatIdAndWorktype(@PathVariable("flatId") Long flatId,@PathVariable("workTypeId") Long workTypeId ) throws CheckListAppException {
		ResponseBean responseBean = assignTaskToFlatService.getTaskByFlatIdAndWorktype(flatId, workTypeId);
		return new ResponseEntity<Object>(responseBean, HttpStatus.OK);
	}

	@GetMapping(value = "/getAssignTaskToFlats")
	public ResponseEntity<Object> getActiveAssignTaskToFlats() throws CheckListAppException {
		ResponseBean responseBean = assignTaskToFlatService.getAssignTaskToFlats();
		return new ResponseEntity<Object>(responseBean, HttpStatus.OK);
	}
	
	@GetMapping(value = "/getAssignTaskToFlatById/{assignTaskToFlatId}")
	public ResponseEntity<Object> getAssignTaskToFlatById(@PathVariable("assignTaskToFlatId") Long assignTaskToFlatId)
			throws CheckListAppException {
		return new ResponseEntity<Object>(assignTaskToFlatService.getAssignTaskToFlatById(assignTaskToFlatId), HttpStatus.OK);
	}
}
