package com.app.beans;

import java.util.Date;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class AssignRoomToFlatBean {
 
	private Long assignRoomToFlatId;

	private Long projectId;

	private Long buildingId;
	
	private Long flatTypeId;

	private Long flatId;
	
	private List<RoomBean> rooms;

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
