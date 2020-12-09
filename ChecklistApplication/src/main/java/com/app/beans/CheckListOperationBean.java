package com.app.beans;

import java.util.List;

import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class CheckListOperationBean {

	private Long checkListOperationBeanId;

	private Long projectId;

	private Long buildingId;

	private Long flatId;

	@ApiModelProperty(hidden = true) 
	private Long ownerId;

	private Long workTypeId;

	@ApiModelProperty(hidden = true) 
	private Long taskId;

	@ApiModelProperty(hidden = true) 
	List<CheckListOperationTaskDetailsBean> taskDetails;

	@ApiModelProperty(hidden = true) 
	private boolean is_owner;

	@ApiModelProperty(hidden = true) 
	private boolean is_user;
}
