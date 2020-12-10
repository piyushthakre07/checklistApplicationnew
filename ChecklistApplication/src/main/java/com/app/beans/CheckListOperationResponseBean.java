package com.app.beans;

import java.util.List;

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
	
	private FloorBean floor;
	//private Long userId;
		
	private WorkTypeBean workType;
	
	private List<CheckListOperationTaskResponseBean> checkListOperationTaskResponseBeanList;
	
}
