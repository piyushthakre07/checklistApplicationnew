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

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@NoArgsConstructor
@Entity
@Table(name = "check_list_operation_defect_image_upload")
@Getter
@Setter
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
	
	@Column(name = "path")
	private String path;
	
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "check_list_operation_task_details_id")
	private CheckListOperationTaskDetails checkListOperationTaskDetails;


}
