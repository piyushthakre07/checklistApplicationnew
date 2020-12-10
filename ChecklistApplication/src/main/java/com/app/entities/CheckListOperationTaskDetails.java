package com.app.entities;

import java.io.Serializable;
import java.util.Date;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
@Entity
@Table(name = "check_list_operation_task_details")
public class CheckListOperationTaskDetails extends AuditMaster implements Serializable {

	private static final long serialVersionUID = 5105934255166289714L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "check_list_operation_task_details_id")
	private Long checkListOperationTaskDetailsId;
	
	@OneToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "task_id")
	private Task task;
	
	@OneToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "room_id")
	private Room room;
	
	@Column(name = "is_check")
	private boolean check;
	
	@Column(name = "is_fault")
	private boolean fault;
	
	@Column(name = "check_list_date")
	private Date checkListDate;
	

	@Column(name = "is_owner")
	private boolean is_owner;
	
	
	@Column(name = "is_user")
	private boolean is_user;

	
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "check_list_operation_id")
	private CheckListOperation checkListOperation;
	
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "checkListOperationTaskDetails")
	private Set<CheckListOperationDefectImageUpload> checkListOperationDefectImageUpload;


}
