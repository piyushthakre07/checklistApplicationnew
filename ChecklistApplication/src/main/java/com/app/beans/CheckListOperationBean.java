package com.app.beans;

import java.util.List;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class CheckListOperationBean {

	private Long checkListOperationBeanId;

	private Long projectId;

	private Long buildingId;

	private Long flatId;

	private Long ownerId;

	private Long workTypeId;

	private Long taskId;

	List<CheckListOperationTaskDetailsBean> taskDetails;
    
	private boolean is_owner;

	private boolean is_user;
}
