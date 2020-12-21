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
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.app.beans.UserLoginBean;
import com.app.exception.CheckListAppException;
import com.app.module.master.service.IUserLoginService;

import io.swagger.annotations.ApiOperation;

/**
 * @author Piyush.Thakre
 * @since 13 sep 2020
 *
 */
@RestController
@CrossOrigin
@RequestMapping("/userLogin")
public class UserLoginController {

	@Autowired
	IUserLoginService userLoginService;

	@GetMapping(value = "/getActiveUserLogins")
	public ResponseEntity<Object> getActiveUserLogins() throws CheckListAppException {
		return new ResponseEntity<Object>(userLoginService.getActiveUserLogins(), HttpStatus.OK);
	}

	@GetMapping(value = "/getUserLogins")
	public ResponseEntity<Object> getUserLogins() throws CheckListAppException {
		return new ResponseEntity<Object>(userLoginService.getUserLogins(), HttpStatus.OK);
	}

	@GetMapping(value = "/getUserLoginByUserLoginId/{userLoginId}")
	public ResponseEntity<Object> getUserLoginByUserLoginId(@PathVariable("userLoginId") Long userLoginId)
			throws CheckListAppException {
		return new ResponseEntity<Object>(userLoginService.getUserLoginByUserLoginId(userLoginId), HttpStatus.OK);
	}
	
	@ApiOperation(value = "login api", response = ResponseEntity.class)
	@PostMapping(value = "/login", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Object> login(@Valid @RequestBody UserLoginBean userLogin)
			throws CheckListAppException {
		return new ResponseEntity<Object>(userLoginService.login(userLogin), HttpStatus.OK);
	}
	
}
