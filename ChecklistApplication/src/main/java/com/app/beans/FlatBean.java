package com.app.beans;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonIgnore;

import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class FlatBean { 

	private Long flatId;

	private String flatName;

	private String description;

	private boolean active;

	private Long projectId;

	private Long buildingId;
	
	private Long floorId;
	
	private Long flatTypeId;
	
	@ApiModelProperty(hidden = true) 
	private ProjectBean project;

	@ApiModelProperty(hidden = true) 
	private BuildingBean building;
	
	@ApiModelProperty(hidden = true)
	private FloorBean floor;
	
	@ApiModelProperty(hidden = true) 
	private FlatTypeBean flatType;

	@JsonIgnore
	private String createdBy;

	@JsonIgnore
	private Date createdDate;

	@JsonIgnore
	private String updatedBy;

	@JsonIgnore
	private Date updatedDate;
}
