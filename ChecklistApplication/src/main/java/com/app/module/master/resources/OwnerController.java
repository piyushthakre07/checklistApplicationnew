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

import com.app.beans.OwnerBean;
import com.app.beans.ResponseBean;
import com.app.exception.CheckListAppException;
import com.app.module.master.service.IOwnerService;

import io.swagger.annotations.ApiOperation;

/**
 * @author Piyush.Thakre
 * @since 13 sep 2020
 *
 */
@RestController
@CrossOrigin
@RequestMapping("/owner")
public class OwnerController {

	@Autowired
	IOwnerService ownerService;

	@ApiOperation(value = "save owner after validation", response = ResponseEntity.class)
	@PostMapping(value = "/insertOwner", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Object> insertOwner(@Valid @RequestBody OwnerBean ownerBean) throws CheckListAppException {
		ResponseBean responseBean = ownerService.insertOrUpdateOwner(ownerBean);
		return new ResponseEntity<Object>(responseBean, HttpStatus.OK);
	}

	@ApiOperation(value = "update owner after validation", response = ResponseEntity.class)
	@PutMapping(value = "/updateOwner", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Object> updateOwner(@Valid @RequestBody OwnerBean ownerBean) throws CheckListAppException {
		ResponseBean responseBean = ownerService.insertOrUpdateOwner(ownerBean);
		return new ResponseEntity<Object>(responseBean, HttpStatus.OK);
	}
	
	@ApiOperation(value = "approve owner by admin", response = ResponseEntity.class)
	@PutMapping(value = "/approveOwner", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Object> approveOwner(@Valid @RequestBody OwnerBean ownerBean) throws CheckListAppException {
		ResponseBean responseBean = ownerService.approveOwner(ownerBean);
		return new ResponseEntity<Object>(responseBean, HttpStatus.OK);
	}

	@GetMapping(value = "/getActiveOwners")
	public ResponseEntity<Object> getActiveOwners() throws CheckListAppException {
		ResponseBean responseBean = ownerService.getActiveOwners();
		return new ResponseEntity<Object>(responseBean, HttpStatus.OK);
	}

	@GetMapping(value = "/getOwners")
	public ResponseEntity<Object> getOwners() throws CheckListAppException {
		ResponseBean responseBean = ownerService.getOwners();
		return new ResponseEntity<Object>(responseBean, HttpStatus.OK);
	}
	
	

}
