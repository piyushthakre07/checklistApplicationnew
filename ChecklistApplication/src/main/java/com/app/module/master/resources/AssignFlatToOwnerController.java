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

import com.app.beans.AssignFlatToOwnerBean;
import com.app.beans.ResponseBean;
import com.app.exception.CheckListAppException;
import com.app.module.master.service.IAssignFlatToOwnerService;

import io.swagger.annotations.ApiOperation;

/**
 * @author Piyush.Thakre
 * @since 18 oct 2020
 *
 */
@RestController
@CrossOrigin
@RequestMapping("/assignFlatToOwner")
public class AssignFlatToOwnerController {

	@Autowired
	IAssignFlatToOwnerService assignFlatToOwnerService;

	@ApiOperation(value = "save assignFlatToOwner after validation", response = ResponseEntity.class)
	@PostMapping(value = "/insertAssignFlatToOwner", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Object> insertAssignFlatToOwner(
			@Valid @RequestBody AssignFlatToOwnerBean assignFlatToOwnerBean) throws CheckListAppException {
		ResponseBean responseBean = assignFlatToOwnerService.insertOrUpdateAssignFlatToOwner(assignFlatToOwnerBean);
		return new ResponseEntity<Object>(responseBean, HttpStatus.OK);
	}

	@ApiOperation(value = "update assignFlatToOwner after validation", response = ResponseEntity.class)
	@PutMapping(value = "/updateAssignFlatToOwner", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Object> updateAssignFlatToOwner(
			@Valid @RequestBody AssignFlatToOwnerBean assignFlatToOwnerBean) throws CheckListAppException {
		ResponseBean responseBean = assignFlatToOwnerService.insertOrUpdateAssignFlatToOwner(assignFlatToOwnerBean);
		return new ResponseEntity<Object>(responseBean, HttpStatus.OK);
	}

	@GetMapping(value = "/getAllActiveAssignFlatToOwner")
	public ResponseEntity<Object> getAllActiveAssignFlatToOwner() throws CheckListAppException {
		ResponseBean responseBean = assignFlatToOwnerService.getActiveAssignFlatToOwners();
		return new ResponseEntity<Object>(responseBean, HttpStatus.OK);
	}

	@GetMapping(value = "/getAssignFlatToOwners")
	public ResponseEntity<Object> getActiveAssignFlatToOwners() throws CheckListAppException {
		ResponseBean responseBean = assignFlatToOwnerService.getAssignFlatToOwners();
		return new ResponseEntity<Object>(responseBean, HttpStatus.OK);
	}
}
