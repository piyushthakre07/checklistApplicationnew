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

import com.app.beans.ContractorBean;
import com.app.beans.ResponseBean;
import com.app.exception.CheckListAppException;
import com.app.module.master.service.IContractorService;

import io.swagger.annotations.ApiOperation;

/**
 * @author Piyush.Thakre
 * @since 13 sep 2020
 *
 */
@RestController
@CrossOrigin
@RequestMapping("/contractor")
public class ContractorController {

	@Autowired
	IContractorService contractorService;

	@ApiOperation(value = "save contractor after validation", response = ResponseEntity.class)
	@PostMapping(value = "/insertContractor", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Object> insertContractor(@Valid @RequestBody ContractorBean contractorBean)
			throws CheckListAppException {
		ResponseBean responseBean = contractorService.insertOrUpdateContractor(contractorBean);
		return new ResponseEntity<Object>(responseBean, HttpStatus.OK);
	}

	@ApiOperation(value = "update contractor after validation", response = ResponseEntity.class)
	@PutMapping(value = "/updateContractor", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Object> updateContractor(@Valid @RequestBody ContractorBean contractorBean)
			throws CheckListAppException {
		ResponseBean responseBean = contractorService.insertOrUpdateContractor(contractorBean);
		return new ResponseEntity<Object>(responseBean, HttpStatus.OK);
	}

	@GetMapping(value = "/getActiveContractors")
	public ResponseEntity<Object> getActiveContractors() throws CheckListAppException {
		ResponseBean responseBean = contractorService.getActiveContractors();
		return new ResponseEntity<Object>(responseBean, HttpStatus.OK);
	}

	@GetMapping(value = "/getContractors")
	public ResponseEntity<Object> getContractors() throws CheckListAppException {
		ResponseBean responseBean = contractorService.getContractors();
		return new ResponseEntity<Object>(responseBean, HttpStatus.OK);
	}
}
