package com.app.entities;

import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
@Entity
@Table(name = "project")
public class Project extends AuditMaster {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "project_id")
	private Long projectId;

	@Column(name = "project_name", length = 30)
	private String projectName;

	@Column(name = "rera_registration", length = 30)
	private String reraRegistration;

	@Column(name = "description", length = 200)
	private String description;
	/*
	 * @OneToMany(fetch = FetchType.EAGER, mappedBy = "project") private
	 * Set<Building> buildings;
	 */
	/*
	 * @OneToMany(fetch = FetchType.EAGER, mappedBy = "project") private Set<Flat>
	 * flat;
	 */

	private boolean active;

}
