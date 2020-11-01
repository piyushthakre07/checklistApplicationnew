package com.app.beans;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class AssignFlatToOwnerBean {
 
	private Long assignFlatToOwnerId;

	private Long projectId;

	private Long buildingId;

	private Long floorId;

	private Long flatId;

	private Long ownerId;

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
