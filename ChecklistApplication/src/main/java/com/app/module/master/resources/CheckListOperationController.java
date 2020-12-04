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

import com.app.beans.CheckListOperationBean;
import com.app.exception.CheckListAppException;
import com.app.module.master.service.ICheckListOperationService;

import io.swagger.annotations.ApiOperation;

/**
 * @author Piyush.Thakre
 * @since 13 sep 2020
 *
 */
@RestController
@CrossOrigin
@RequestMapping("/checkListOperation")
public class CheckListOperationController {

	@Autowired
	ICheckListOperationService checkListOperationService;

	@ApiOperation(value = "save checkListOperation after validation", response = ResponseEntity.class)
	@PostMapping(value = "/insertCheckListOperation", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Object> insertCheckListOperation(@Valid @RequestBody CheckListOperationBean checkListOperationBean)
			throws CheckListAppException {
		return new ResponseEntity<Object>(checkListOperationService.insertOrUpdateCheckListOperation(checkListOperationBean), HttpStatus.OK);
	}

	@ApiOperation(value = "update checkListOperation after validation", response = ResponseEntity.class)
	@PutMapping(value = "/updateCheckListOperation", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Object> updateCheckListOperation(@Valid @RequestBody CheckListOperationBean checkListOperationBean)
			throws CheckListAppException {
		return new ResponseEntity<Object>(checkListOperationService.insertOrUpdateCheckListOperation(checkListOperationBean), HttpStatus.OK);
	}


	@GetMapping(value = "/getCheckListOperations")
	public ResponseEntity<Object> getCheckListOperations() throws CheckListAppException {
		return new ResponseEntity<Object>(checkListOperationService.getCheckListOperations(), HttpStatus.OK);
	}

	@GetMapping(value = "/getCheckListOperationByCheckListOperationId/{checkListOperationId}")
	public ResponseEntity<Object> getCheckListOperationByCheckListOperationId(@PathVariable("checkListOperationId") Long checkListOperationId)
			throws CheckListAppException {
		return new ResponseEntity<Object>(checkListOperationService.getCheckListOperationByCheckListOperationId(checkListOperationId), HttpStatus.OK);
	}
	
	
	@ApiOperation(value = "CheckListOperation Report", response = ResponseEntity.class)
	@PostMapping(value = "/getCheckListOperationReport", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Object> getCheckListOperationReport(@RequestBody CheckListOperationBean checkListOperationBean)
			throws CheckListAppException {
		return new ResponseEntity<Object>(checkListOperationService.getCheckListOperationReport(checkListOperationBean), HttpStatus.OK);
	}

}
