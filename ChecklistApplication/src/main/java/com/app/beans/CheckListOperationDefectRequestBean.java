package com.app.beans;

import java.util.Date;

import org.springframework.web.multipart.MultipartFile;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class CheckListOperationDefectRequestBean {

	private Long checkListOperationBeanId;

	private Long projectId;

	private Long buildingId;

	private Long flatId;

	private Long ownerId;

	private Long workTypeId;

	private Long taskId;

	private Long roomId;
	
	private boolean fault;
	
	private Date checkListDate;
	
	private String faultRemark;
	
	private MultipartFile[] uploadImages;
 
	private boolean isOwner;

	private boolean isUser;
}
