package com.app.beans;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonIgnore;

import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class AssignFlatToOwnerResponseBean {

	private Long assignFlatToOwnerId;

	private ProjectBean project;

	private BuildingBean building;

	private FloorBean floor;

	private FlatBean flat;

	private OwnerBean owner;

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
