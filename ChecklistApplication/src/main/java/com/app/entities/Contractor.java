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
@Table(name = "contractor")
public class Contractor extends AuditMaster {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "Contractor_id")
	private Long contractorId;

	@Column(name = "Contractor_name", length = 30)
	private String contractorName;

	@Column(name = "contact_number", length = 10)
	private String contactNumber;
	
	@Column(name = "alternate_contact_number", length = 10)
	private String alternateContactNumber;
	
	@Column(name = "email_address", length = 30)
	private String emailAddress;
	
	@Column(name = "address", length = 200)
	private String address;
	
	@Column(name = "other_details", length = 200)
	private String otherDetails;

	private boolean active;

}
