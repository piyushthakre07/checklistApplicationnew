package com.app.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
@Entity
@Table(name = "work_type")
public class WorkType extends AuditMaster {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "work_type_id")
	private Long workTypeId;

	@Column(name = "work_type", length = 200)
	private String workType;

	@Column(name = "description", length = 200)
	private String description;

	private boolean active;
	
	

}
