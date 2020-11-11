package com.app.entities;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
@Entity
@Table(name = "check_list_operation")
public class CheckListOperation extends AuditMaster {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "check_list_operation_id")
	private Long checkListOperationId;
	
	@Column(name = "check_list_date")
	private Date checkListDate;

	@OneToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "project_id")
	private Project project;

	@OneToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "building_id")
	private Building building;

	@OneToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "flat_type_id",nullable = true)
	private FlatType flatType;

	@OneToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "flat_id")
	private Flat flat;
	
	@OneToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "owner_id",nullable = true)
	private Owner owner;
	
	@OneToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "work_type_id")
	private WorkType workType;
	
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


}
