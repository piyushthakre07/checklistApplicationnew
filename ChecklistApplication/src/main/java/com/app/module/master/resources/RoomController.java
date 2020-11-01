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

import com.app.beans.RoomBean;
import com.app.beans.ResponseBean;
import com.app.exception.CheckListAppException;
import com.app.module.master.service.IRoomService;

import io.swagger.annotations.ApiOperation;

/**
 * @author Piyush.Thakre
 * @since 13 sep 2020
 *
 */
@RestController
@CrossOrigin
@RequestMapping("/room")
public class RoomController {

	@Autowired
	IRoomService roomService;

	@ApiOperation(value = "save room after validation", response = ResponseEntity.class)
	@PostMapping(value = "/insertRoom", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Object> insertRoom(@Valid @RequestBody RoomBean roomBean) throws CheckListAppException {
		ResponseBean responseBean = roomService.insertOrUpdateRoom(roomBean);
		return new ResponseEntity<Object>(responseBean, HttpStatus.OK);
	}

	@ApiOperation(value = "update room after validation", response = ResponseEntity.class)
	@PutMapping(value = "/updateRoom", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Object> updateRoom(@Valid @RequestBody RoomBean roomBean) throws CheckListAppException {
		ResponseBean responseBean = roomService.insertOrUpdateRoom(roomBean);
		return new ResponseEntity<Object>(responseBean, HttpStatus.OK);
	}

	@GetMapping(value = "/getActiveRooms")
	public ResponseEntity<Object> getActiveRooms() throws CheckListAppException {
		ResponseBean responseBean = roomService.getActiveRooms();
		return new ResponseEntity<Object>(responseBean, HttpStatus.OK);
	}

	@GetMapping(value = "/getRooms")
	public ResponseEntity<Object> getRooms() throws CheckListAppException {
		ResponseBean responseBean = roomService.getRooms();
		return new ResponseEntity<Object>(responseBean, HttpStatus.OK);
	}
}
