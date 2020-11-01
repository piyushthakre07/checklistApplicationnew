package com.app.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
@Entity
@Table(name = "flat")
public class Flat extends AuditMaster {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "flat_id")
	private Long flatId;

	@Column(name = "flat_name", length = 30)
	private String flatName;
	
	@Column(name = "description", length = 200)
	private String description;

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "project_id")
	private Project project;

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "building_id")
	private Building building;
	
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "floor_id")
	private Floor floor;
	
	@OneToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "flat_type_id")
	private FlatType flatType;
	
	@ManyToOne
    @JoinColumn(name = "owner_id")
    private Owner owner;

	private boolean active;

}
