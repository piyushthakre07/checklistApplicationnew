package com.app.beans;

import java.util.Date;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class CheckListOperationTaskDetailsBean {

private Long roomId;
	
private boolean check;

private boolean fault;

private Date checkListDate;

}
