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

import com.app.beans.AssignWorkToContractorBean;
import com.app.beans.ResponseBean;
import com.app.exception.CheckListAppException;
import com.app.module.master.service.IAssignWorkToContractorService;

import io.swagger.annotations.ApiOperation;

/**
 * @author Piyush.Thakre
 * @since 18 oct 2020
 *
 */
@RestController
@CrossOrigin
@RequestMapping("/assignWorkToContractor")
public class AssignWorkToContractorController {

	@Autowired
	IAssignWorkToContractorService assignWorkToContractorService;

	@ApiOperation(value = "save assignWorkToContractor after validation", response = ResponseEntity.class)
	@PostMapping(value = "/insertAssignWorkToContractor", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Object> insertAssignWorkToContractor(
			@Valid @RequestBody AssignWorkToContractorBean assignWorkToContractorBean) throws CheckListAppException {
		ResponseBean responseBean = assignWorkToContractorService
				.insertOrUpdateAssignWorkToContractor(assignWorkToContractorBean);
		return new ResponseEntity<Object>(responseBean, HttpStatus.OK);
	}

	@ApiOperation(value = "update assignWorkToContractor after validation", response = ResponseEntity.class)
	@PutMapping(value = "/updateAssignWorkToContractor", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Object> updateAssignWorkToContractor(
			@Valid @RequestBody AssignWorkToContractorBean assignWorkToContractorBean) throws CheckListAppException {
		ResponseBean responseBean = assignWorkToContractorService
				.insertOrUpdateAssignWorkToContractor(assignWorkToContractorBean);
		return new ResponseEntity<Object>(responseBean, HttpStatus.OK);
	}

	@GetMapping(value = "/getAllActiveAssignWorkToContractor")
	public ResponseEntity<Object> getAllActiveAssignWorkToContractor() throws CheckListAppException {
		ResponseBean responseBean = assignWorkToContractorService.getActiveAssignWorkToContractors();
		return new ResponseEntity<Object>(responseBean, HttpStatus.OK);
	}

	@GetMapping(value = "/getAssignWorkToContractors")
	public ResponseEntity<Object> getActiveAssignWorkToContractors() throws CheckListAppException {
		ResponseBean responseBean = assignWorkToContractorService.getAssignWorkToContractors();
		return new ResponseEntity<Object>(responseBean, HttpStatus.OK);
	}
}
