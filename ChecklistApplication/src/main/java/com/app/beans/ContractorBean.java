package com.app.beans;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class ContractorBean {

	private Long contractorId;

	private String contractorName;

	private String contactNumber;
	
	private String alternateContactNumber;
	
	private String emailAddress;
	
	private String address;
	
	private String otherDetails;

	private boolean active;

	@JsonIgnore
	private String createdBy;

	@JsonIgnore
	private Date createdDate;

	@JsonIgnore
	private String updatedBy;

	@JsonIgnore
	private Date updatedDate;

}
