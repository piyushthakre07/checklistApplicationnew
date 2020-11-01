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

import com.app.beans.BuildingBean;
import com.app.beans.BuildingRequestBean;
import com.app.beans.ResponseBean;
import com.app.exception.CheckListAppException;
import com.app.module.master.service.IBuildingService;

import io.swagger.annotations.ApiOperation;

/**
 * @author Piyush.Thakre
 * @since 13 sep 2020
 *
 */
@RestController
@CrossOrigin
@RequestMapping("/building")
public class BuildingController {

	@Autowired
	IBuildingService buildingService;

	@ApiOperation(value = "save building after validation", response = ResponseEntity.class)
	@PostMapping(value = "/insertBuilding", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Object> insertBuilding(@Valid @RequestBody BuildingBean buildingBean)
			throws CheckListAppException {
		ResponseBean responseBean = buildingService.insertOrUpdateBuilding(buildingBean);
		return new ResponseEntity<Object>(responseBean, HttpStatus.OK);
	}

	@ApiOperation(value = "save multiple building for particular after validation", response = ResponseEntity.class)
	@PostMapping(value = "/insertMultipleBuilding", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Object> insertMultipleBuilding(@Valid @RequestBody BuildingRequestBean buildingRequestBean)
			throws CheckListAppException {
		ResponseBean responseBean = buildingService.insertMultipleBuilding(buildingRequestBean);
		return new ResponseEntity<Object>(responseBean, HttpStatus.OK);
	}

	@ApiOperation(value = "update building after validation", response = ResponseEntity.class)
	@PutMapping(value = "/updateBuilding", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Object> updateBuilding(@Valid @RequestBody BuildingBean buildingBean)
			throws CheckListAppException {
		ResponseBean responseBean = buildingService.insertOrUpdateBuilding(buildingBean);
		return new ResponseEntity<Object>(responseBean, HttpStatus.OK);
	}

	@GetMapping(value = "/getActiveBuilding")
	public ResponseEntity<Object> getActiveBuilding() throws CheckListAppException {
		ResponseBean responseBean = buildingService.getActiveBuildings();
		return new ResponseEntity<Object>(responseBean, HttpStatus.OK);
	}

	@GetMapping(value = "/getActiveBuildingsByProjectId/{projectId}")
	public ResponseEntity<Object> getActiveBuildingsByProjectId(@PathVariable("projectId") Long projectId)
			throws CheckListAppException {
		ResponseBean responseBean = buildingService.getActiveBuildingsByProjectId(projectId);
		return new ResponseEntity<Object>(responseBean, HttpStatus.OK);
	}
	
	@GetMapping(value = "/getBuildingByBuildingId/{buildingId}")
	public ResponseEntity<Object> getBuildingByBuildingId(@PathVariable("buildingId") Long buildingId)
			throws CheckListAppException {
		ResponseBean responseBean = buildingService.getBuildingByBuildingId(buildingId);
		return new ResponseEntity<Object>(responseBean, HttpStatus.OK);
	}

	@GetMapping(value = "/getBuildings")
	public ResponseEntity<Object> getActiveBuildings() throws CheckListAppException {
		ResponseBean responseBean = buildingService.getBuildings();
		return new ResponseEntity<Object>(responseBean, HttpStatus.OK);
	}
}
