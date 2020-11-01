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

import com.app.beans.FlatBean;
import com.app.beans.FlatRequestBean;
import com.app.beans.ResponseBean;
import com.app.exception.CheckListAppException;
import com.app.module.master.service.IFlatService;

import io.swagger.annotations.ApiOperation;

/**
 * @author Piyush.Thakre
 * @since 13 sep 2020
 *
 */
@RestController
@CrossOrigin
@RequestMapping("/flat")
public class FlatController {

	@Autowired
	IFlatService flatService;

	@ApiOperation(value = "save flat after validation", response = ResponseEntity.class)
	@PostMapping(value = "/insertFlat", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Object> insertFlat(@Valid @RequestBody FlatBean flatBean) throws CheckListAppException {
		ResponseBean responseBean = flatService.insertOrUpdateFlat(flatBean);
		return new ResponseEntity<Object>(responseBean, HttpStatus.OK);
	}

	@ApiOperation(value = "save multiple flat for particular after validation", response = ResponseEntity.class)
	@PostMapping(value = "/insertMultipleFlat", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Object> insertMultipleFlat(@Valid @RequestBody FlatRequestBean flatRequestBean)
			throws CheckListAppException {
		ResponseBean responseBean = flatService.insertMultipleFlat(flatRequestBean);
		return new ResponseEntity<Object>(responseBean, HttpStatus.OK);
	}

	@ApiOperation(value = "update flat after validation", response = ResponseEntity.class)
	@PutMapping(value = "/updateFlat", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Object> updateFlat(@Valid @RequestBody FlatBean flatBean) throws CheckListAppException {
		ResponseBean responseBean = flatService.insertOrUpdateFlat(flatBean);
		return new ResponseEntity<Object>(responseBean, HttpStatus.OK);
	}

	@GetMapping(value = "/getAllActiveFlat")
	public ResponseEntity<Object> getAllActiveFlat() throws CheckListAppException {
		ResponseBean responseBean = flatService.getActiveFlats();
		return new ResponseEntity<Object>(responseBean, HttpStatus.OK);
	}

	@GetMapping(value = "/getActiveFlatsByFloorId/{floorId}")
	public ResponseEntity<Object> getActiveFlatsByFloorId(Long floorId) throws CheckListAppException {
		ResponseBean responseBean = flatService.getActiveFlatsByFloorId(floorId);
		return new ResponseEntity<Object>(responseBean, HttpStatus.OK);
	}

	@GetMapping(value = "/getActiveFlatsByBuildingId/{buildingId}")
	public ResponseEntity<Object> getActiveFlatsByBuildingId(Long buildingId) throws CheckListAppException {
		ResponseBean responseBean = flatService.getActiveFlatsByBuildingId(buildingId);
		return new ResponseEntity<Object>(responseBean, HttpStatus.OK);
	}

	@GetMapping(value = "/getFlats")
	public ResponseEntity<Object> getActiveFlats() throws CheckListAppException {
		ResponseBean responseBean = flatService.getFlats();
		return new ResponseEntity<Object>(responseBean, HttpStatus.OK);
	}
	
	@GetMapping(value = "/getFlatByFlatId/{flatId}")
	public ResponseEntity<Object> getFlatByFlatId(@PathVariable("flatId") Long flatId)
			throws CheckListAppException {
		ResponseBean responseBean = flatService.getFlatByFlatId(flatId);
		return new ResponseEntity<Object>(responseBean, HttpStatus.OK);
	}
}
