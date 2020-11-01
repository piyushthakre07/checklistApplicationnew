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

import com.app.beans.FlatTypeBean;
import com.app.beans.ResponseBean;
import com.app.exception.CheckListAppException;
import com.app.module.master.service.IFlatTypeService;

import io.swagger.annotations.ApiOperation;

/**
 * @author Piyush.Thakre
 * @since 13 sep 2020
 *
 */
@RestController
@CrossOrigin
@RequestMapping("/flatType")
public class FlatTypeController {

	@Autowired
	IFlatTypeService flatTypeService;

	@ApiOperation(value = "save flatType after validation", response = ResponseEntity.class)
	@PostMapping(value = "/insertFlatType", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Object> insertFlatType(@Valid @RequestBody FlatTypeBean flatTypeBean)
			throws CheckListAppException {
		ResponseBean responseBean = flatTypeService.insertOrUpdateFlatType(flatTypeBean);
		return new ResponseEntity<Object>(responseBean, HttpStatus.OK);
	}

	@ApiOperation(value = "update flatType after validation", response = ResponseEntity.class)
	@PutMapping(value = "/updateFlatType", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Object> updateFlatType(@Valid @RequestBody FlatTypeBean flatTypeBean)
			throws CheckListAppException {
		ResponseBean responseBean = flatTypeService.insertOrUpdateFlatType(flatTypeBean);
		return new ResponseEntity<Object>(responseBean, HttpStatus.OK);
	}

	@GetMapping(value = "/getActiveFlatTypes")
	public ResponseEntity<Object> getActiveFlatTypes() throws CheckListAppException {
		ResponseBean responseBean = flatTypeService.getActiveFlatTypes();
		return new ResponseEntity<Object>(responseBean, HttpStatus.OK);
	}

	@GetMapping(value = "/getFlatTypes")
	public ResponseEntity<Object> getFlatTypes() throws CheckListAppException {
		ResponseBean responseBean = flatTypeService.getFlatTypes();
		return new ResponseEntity<Object>(responseBean, HttpStatus.OK);
	}
}
