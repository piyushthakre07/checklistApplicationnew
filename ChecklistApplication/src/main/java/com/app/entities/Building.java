package com.app.entities;

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
@Table(name = "building")
public class Building extends AuditMaster{
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "building_id")
	private Long buildingId;
	
	@Column(name = "building_name", length = 30)
	private String buildingName;

	@Column(name = "description", length = 200)
	private String description;

	@OneToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "project_id")
	private Project project;
	/*
	 * @OneToMany(fetch = FetchType.EAGER, mappedBy = "building") private Set<Floor>
	 * floor;
	 * 
	 * @OneToMany(fetch = FetchType.EAGER, mappedBy = "building") private Set<Flat>
	 * flat;
	 */
	
	private boolean active;

	
}
