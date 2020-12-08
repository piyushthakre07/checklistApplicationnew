package com.app.beans;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class AssignRoomToFlatResponseBean {

	private Long assignRoomToFlatId;

	private ProjectBean project;

	private BuildingBean building;

	private FlatBean flat;

	private FlatTypeBean flatType;
	
	private RoomBean room;

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
