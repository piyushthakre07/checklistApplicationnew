package com.app.beans;

import java.util.Date;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class CheckListOperationTaskResponseBean {

	private TaskBean task;

	private RoomBean room;

	private ContractorBean contractor;

	private Date checkListDate;

	private Boolean ownerChecked;

	private Boolean userChecked;

	private Boolean faultOwner;

	private Boolean faultUser;
	
	private String faultRemark;
	

}
