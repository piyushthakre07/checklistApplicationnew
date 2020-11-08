package com.app.beans;

import java.io.Serializable;
import java.util.Date;

import com.fasterxml.jackson.annotation.JsonIgnore;

import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class BuildingBean implements Serializable{

	@JsonIgnore
	private static final long serialVersionUID = 1L;

	private Long buildingId;

	private String buildingName;

	private String description;

	private boolean active;

	private Long projectId;
	
	@ApiModelProperty(hidden = true) 
	private ProjectBean project;

	@JsonIgnore
	private String createdBy;

	@JsonIgnore
	private Date createdDate;

	@JsonIgnore
	private String updatedBy;

	@JsonIgnore
	private Date updatedDate;
	
	
}
