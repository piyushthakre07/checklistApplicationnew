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

import com.app.beans.AssignRoomToFlatBean;
import com.app.beans.ResponseBean;
import com.app.exception.CheckListAppException;
import com.app.module.master.service.IAssignRoomToFlatService;

import io.swagger.annotations.ApiOperation;

/**
 * @author Piyush.Thakre
 * @since 18 oct 2020
 *
 */
@RestController
@CrossOrigin
@RequestMapping("/assignRoomToFlat")
public class AssignRoomToFlatController {

	@Autowired
	IAssignRoomToFlatService assignRoomToFlatService;

	@ApiOperation(value = "save assignRoomToFlat after validation", response = ResponseEntity.class)
	@PostMapping(value = "/insertAssignRoomToFlat", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Object> insertAssignRoomToFlat(
			@Valid @RequestBody AssignRoomToFlatBean assignRoomToFlatBean) throws CheckListAppException {
		ResponseBean responseBean = assignRoomToFlatService.insertOrUpdateAssignRoomToFlat(assignRoomToFlatBean);
		return new ResponseEntity<Object>(responseBean, HttpStatus.OK);
	}

	@ApiOperation(value = "update assignRoomToFlat after validation", response = ResponseEntity.class)
	@PutMapping(value = "/updateAssignRoomToFlat", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Object> updateAssignRoomToFlat(
			@Valid @RequestBody AssignRoomToFlatBean assignRoomToFlatBean) throws CheckListAppException {
		ResponseBean responseBean = assignRoomToFlatService.insertOrUpdateAssignRoomToFlat(assignRoomToFlatBean);
		return new ResponseEntity<Object>(responseBean, HttpStatus.OK);
	}

	@GetMapping(value = "/getAllActiveAssignRoomToFlat")
	public ResponseEntity<Object> getAllActiveAssignRoomToFlat() throws CheckListAppException {
		ResponseBean responseBean = assignRoomToFlatService.getActiveAssignRoomToFlats();
		return new ResponseEntity<Object>(responseBean, HttpStatus.OK);
	}

	@GetMapping(value = "/getAssignRoomToFlats")
	public ResponseEntity<Object> getActiveAssignRoomToFlats() throws CheckListAppException {
		ResponseBean responseBean = assignRoomToFlatService.getAssignRoomToFlats();
		return new ResponseEntity<Object>(responseBean, HttpStatus.OK);
	}
}
