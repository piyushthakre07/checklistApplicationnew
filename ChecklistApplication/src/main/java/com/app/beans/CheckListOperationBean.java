package com.app.beans;

import java.util.Date;
import java.util.List;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class CheckListOperationBean {

	private Long checkListOperationBeanId;
	
	private Long projectId;

	private Long buildingId;
	
	private Long flatTypeId;

	private Long flatId;
	
	private Long ownerId;
	
	//private Long userId;
	
	private Long workTypeId;
	
	private Long taskId;
	
	private List<Long> roomIds;
	
	private boolean check;
	
	private boolean fault;
	
	private Date checkListDate;
	
	private boolean is_owner;
	
	private boolean is_user;
}
