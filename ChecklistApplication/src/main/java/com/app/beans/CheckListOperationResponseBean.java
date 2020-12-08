package com.app.beans;

import java.util.Date;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class CheckListOperationResponseBean {

	private Long checkListOperationBeanId;
	
	private ProjectBean project;

	private BuildingBean building;
	
	private FlatTypeBean flatType;

	private FlatBean flat;
	
	private OwnerBean owner;
	
	//private Long userId;
		
	private WorkTypeBean workType;
	
	private TaskBean task;
	
	private RoomBean room;
	
	private ContractorBean contractor;
	
	private boolean check;
	
	private boolean fault;
	
	private Date checkListDate;
	
	private boolean is_owner;
	
	private boolean is_user;
}
