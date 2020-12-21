package com.app.beans;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonIgnore;

import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class EmployeeBean { 

	private Long employeeId;

	private String employeeName;
	
	private String contactNumber;
	
	private String alternateContactNumber;
	
	private String emailAddress;
	
	private String address;
	
	private String description;
	
	private UserLoginBean userLogin;

	@ApiModelProperty(hidden = true)
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
