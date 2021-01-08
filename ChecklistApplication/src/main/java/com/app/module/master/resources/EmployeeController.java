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

import com.app.beans.AproveEmployeeRequestBean;
import com.app.beans.EmployeeBean;
import com.app.beans.ResponseBean;
import com.app.exception.CheckListAppException;
import com.app.module.master.service.IEmployeeService;

import io.swagger.annotations.ApiOperation;

/**
 * @author Piyush.Thakre
 * @since 14 dec 2020
 *
 */
@RestController
@CrossOrigin
@RequestMapping("/employee")
public class EmployeeController {

	@Autowired
	IEmployeeService employeeService;

	@ApiOperation(value = "save employee after validation", response = ResponseEntity.class)
	@PostMapping(value = "/insertEmployee", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Object> insertEmployee(@Valid @RequestBody EmployeeBean employeeBean) throws CheckListAppException {
		ResponseBean responseBean = employeeService.insertOrUpdateEmployee(employeeBean);
		return new ResponseEntity<Object>(responseBean, HttpStatus.OK);
	}

	@ApiOperation(value = "update employee after validation", response = ResponseEntity.class)
	@PutMapping(value = "/updateEmployee", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Object> updateEmployee(@Valid @RequestBody EmployeeBean employeeBean) throws CheckListAppException {
		ResponseBean responseBean = employeeService.insertOrUpdateEmployee(employeeBean);
		return new ResponseEntity<Object>(responseBean, HttpStatus.OK);
	}
	
	@ApiOperation(value = "approve employee by admin", response = ResponseEntity.class)
	@PutMapping(value = "/approveEmployee", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Object> approveEmployee( @RequestBody AproveEmployeeRequestBean aproveEmployeeRequestBean) throws CheckListAppException {
		ResponseBean responseBean = employeeService.approveEmployee(aproveEmployeeRequestBean);
		return new ResponseEntity<Object>(responseBean, HttpStatus.OK);
	}

	@GetMapping(value = "/getActiveEmployees")
	public ResponseEntity<Object> getActiveEmployees() throws CheckListAppException {
		ResponseBean responseBean = employeeService.getActiveEmployees();
		return new ResponseEntity<Object>(responseBean, HttpStatus.OK);
	}

	@GetMapping(value = "/getEmployees")
	public ResponseEntity<Object> getEmployees() throws CheckListAppException {
		ResponseBean responseBean = employeeService.getEmployees();
		return new ResponseEntity<Object>(responseBean, HttpStatus.OK);
	}
	
	

}
