package com.app.beans;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonIgnore;

import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class FloorBean {

	private Long floorId;

	private String floorName;

	private String description;

	private boolean active;

	private Long projectId;

	private Long buildingId;
	
	@ApiModelProperty(hidden = true) 
	private ProjectBean project;

	@ApiModelProperty(hidden = true) 
	private BuildingBean building;

	@JsonIgnore
	private String createdBy;

	@JsonIgnore
	private Date createdDate;

	@JsonIgnore
	private String updatedBy;

	@JsonIgnore
	private Date updatedDate;
}
