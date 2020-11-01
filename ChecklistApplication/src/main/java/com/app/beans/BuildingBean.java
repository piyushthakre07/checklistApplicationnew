package com.app.beans;

import java.io.Serializable;
import java.util.Date;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

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

	@NotBlank(message = "Building Name required and should not be blank or empty")
	@ApiModelProperty(value = "buildingName", required = true)
	@Size(min = 3, max = 30, message = "Building name must be between 3 and 30 characters")
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
