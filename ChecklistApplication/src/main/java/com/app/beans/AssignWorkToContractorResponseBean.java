package com.app.beans;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonIgnore;

import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class AssignWorkToContractorResponseBean { 

	private Long assignWorkToContractorId;

	private ProjectBean project;

	private BuildingBean building;
	
	@ApiModelProperty(hidden = true) 
	private FloorBean floor;
	
	private FlatBean flat;
	
	private WorkTypeBean workType;
	
	private ContractorBean contractor;
	
	private boolean active;

	@JsonIgnore
	private String createdBy;

	@JsonIgnore
	private Date createdDate;

	@JsonIgnore
	private String updatedBy;

	@JsonIgnore
	private Date updatedDate;
}
