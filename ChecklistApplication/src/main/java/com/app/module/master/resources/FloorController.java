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

import com.app.beans.FloorBean;
import com.app.beans.FloorRequestBean;
import com.app.beans.ResponseBean;
import com.app.exception.CheckListAppException;
import com.app.module.master.service.IFloorService;

import io.swagger.annotations.ApiOperation;

/**
 * @author Piyush.Thakre
 * @since 13 sep 2020
 *
 */
@RestController
@CrossOrigin
@RequestMapping("/floor")
public class FloorController {

	@Autowired
	IFloorService floorService;

	@ApiOperation(value = "save floor after validation", response = ResponseEntity.class)
	@PostMapping(value = "/insertFloor", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Object> insertFloor(@Valid @RequestBody FloorBean floorBean) throws CheckListAppException {
		ResponseBean responseBean = floorService.insertOrUpdateFloor(floorBean);
		return new ResponseEntity<Object>(responseBean, HttpStatus.OK);
	}

	@ApiOperation(value = "save multiple floor for particular after validation", response = ResponseEntity.class)
	@PostMapping(value = "/insertMultipleFloor", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Object> insertMultipleFloor(@Valid @RequestBody FloorRequestBean floorRequestBean)
			throws CheckListAppException {
		ResponseBean responseBean = floorService.insertMultipleFloor(floorRequestBean);
		return new ResponseEntity<Object>(responseBean, HttpStatus.OK);
	}

	@ApiOperation(value = "update floor after validation", response = ResponseEntity.class)
	@PutMapping(value = "/updateFloor", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Object> updateFloor(@Valid @RequestBody FloorBean floorBean) throws CheckListAppException {
		ResponseBean responseBean = floorService.insertOrUpdateFloor(floorBean);
		return new ResponseEntity<Object>(responseBean, HttpStatus.OK);
	}

	@GetMapping(value = "/getAllActiveFloor")
	public ResponseEntity<Object> getAllActiveFloor() throws CheckListAppException {
		ResponseBean responseBean = floorService.getActiveFloors();
		return new ResponseEntity<Object>(responseBean, HttpStatus.OK);
	}

	@GetMapping(value = "/getActiveFloorsBybuildingId/{buildingId}")
	public ResponseEntity<Object> getActiveFloorsBybuildingId(@PathVariable("buildingId") Long buildingId)
			throws CheckListAppException {
		ResponseBean responseBean = floorService.getActiveFloorsBybuildingId(buildingId);
		return new ResponseEntity<Object>(responseBean, HttpStatus.OK);
	}

	@GetMapping(value = "/getFloorByFloorId/{floorId}")
	public ResponseEntity<Object> getFloorByFloorId(@PathVariable("floorId") Long floorId)
			throws CheckListAppException {
		ResponseBean responseBean = floorService.getFloorByFloorId(floorId);
		return new ResponseEntity<Object>(responseBean, HttpStatus.OK);
	}

	@GetMapping(value = "/getFloors")
	public ResponseEntity<Object> getActiveFloors() throws CheckListAppException {
		ResponseBean responseBean = floorService.getFloors();
		return new ResponseEntity<Object>(responseBean, HttpStatus.OK);
	}
}
