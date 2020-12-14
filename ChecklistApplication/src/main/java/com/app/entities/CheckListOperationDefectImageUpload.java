package com.app.entities;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import lombok.NoArgsConstructor;


@NoArgsConstructor
@Entity
@Table(name = "check_list_operation_defect_image_upload")
public class CheckListOperationDefectImageUpload extends AuditMaster implements Serializable {

	private static final long serialVersionUID = 5105934255166289714L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "check_list_operation_defect_image_uploadId")
	private Long checkListOperationDefectImageUploadId;

	@Column(name = "name")
	private String name;

	@Column(name = "type")
	private String type;
	
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "check_list_operation_task_details_id")
	private CheckListOperationTaskDetails checkListOperationTaskDetails;

	@Column(name = "picByte")
	private byte[] picByte;

	public Long getCheckListOperationDefectImageUploadId() {
		return checkListOperationDefectImageUploadId;
	}

	public void setCheckListOperationDefectImageUploadId(Long checkListOperationDefectImageUploadId) {
		this.checkListOperationDefectImageUploadId = checkListOperationDefectImageUploadId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public CheckListOperationTaskDetails getCheckListOperationTaskDetails() {
		return checkListOperationTaskDetails;
	}

	public void setCheckListOperationTaskDetails(CheckListOperationTaskDetails checkListOperationTaskDetails) {
		this.checkListOperationTaskDetails = checkListOperationTaskDetails;
	}

	public byte[] getPicByte() {
		return picByte;
	}

	public void setPicByte(byte[] picByte) {
		this.picByte = picByte;
	}
	
	

}
