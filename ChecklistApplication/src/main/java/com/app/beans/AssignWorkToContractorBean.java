package com.app.beans;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonIgnore;

import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class AssignWorkToContractorBean { 

	private Long assignWorkToContractorId;

	private Long projectId;

	private Long buildingId;
	
	private Long floorId;
	
	private Long flatId;
	
	private Long workTypeId;
	
	private Long contractorId;
	
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
