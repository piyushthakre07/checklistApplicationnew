package com.app.beans;

import java.util.Date;

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
	
	private Long roomId;
	
	private boolean check;
	
	private boolean fault;
	
	private Date checkListDate;
}
