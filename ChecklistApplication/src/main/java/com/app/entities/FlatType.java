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
@Table(name = "flat_type")
public class FlatType extends AuditMaster {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "flat_type_id")
	private Long flatTypeId;

	@Column(name = "flat_type", length = 30)
	private String flatType;

	@Column(name = "description", length = 200)
	private String description;

	private boolean active;

}
