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
@Table(name = "employee")
public class Employee extends AuditMaster {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "employee_id")
	private Long employeeId;

	@Column(name = "employee_name", length = 30)
	private String employeeName;

	@Column(name = "contact_number", length = 30)
    private String contactNumber;
	
	@Column(name = "alternate_contact_number", length = 30)
	private String alternateContactNumber;
	
	@Column(name = "email_address", length = 30)
	private String emailAddress;
	
	@Column(name = "address", length = 30)
	private String address;
	
	@Column(name = "description", length = 200)
	private String description;

	private boolean active;

}
