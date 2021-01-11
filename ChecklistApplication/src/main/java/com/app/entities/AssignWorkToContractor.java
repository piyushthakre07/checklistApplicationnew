package com.app.entities;

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
@Table(name = "assign_work_to_contractor")
public class AssignWorkToContractor extends AuditMaster{

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "assignWorkToContractor_id")
	private Long assignWorkToContractorId;
	
	@OneToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "project_id")
	private Project project;

	@OneToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "building_id")
	private Building building;
	
	@OneToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "floor_id")
	private Floor floor;
	
	@OneToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "flat_id")
	private Flat flat;
	
	@OneToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "work_type_id")
	private WorkType workType;

	@OneToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "Contractor_id")
	private Contractor contractor;
	
	private boolean active;

}
