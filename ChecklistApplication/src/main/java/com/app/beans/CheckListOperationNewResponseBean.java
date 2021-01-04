package com.app.beans;

import java.util.List;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class CheckListOperationNewResponseBean {

	private Long checkListOperationBeanId;

	private ProjectBean project;

	private BuildingBean building;

	private FlatTypeBean flatType;

	private FlatBean flat;

	private OwnerBean owner;

	private FloorBean floor;

	private List<RoomBean> headerRooms;

	private List<WorkTypeResponseBean> workTypes;

}
