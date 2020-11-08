package com.app.beans;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class AssignTaskToFlatResponseBean {

	private Long assignTaskToFlatId;

	private ProjectBean project;

	private BuildingBean building;
	
	private FlatTypeBean flatType;

	private FlatBean flat;

	private WorkTypeBean workTypeBean;
	
	private TaskBean taskBean;

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
