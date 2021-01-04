package com.app.beans;

import java.util.List;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class TaskResponseBean {

	private Long taskId;

	private String task;

	private String description;

	private boolean active;
	
	private List<RoomResponseBean> rooms;
	
}
