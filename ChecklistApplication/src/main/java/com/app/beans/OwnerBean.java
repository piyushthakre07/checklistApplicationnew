package com.app.beans;

import java.util.Date;

import javax.validation.constraints.NotBlank;

import com.fasterxml.jackson.annotation.JsonIgnore;

import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class OwnerBean { 

	private Long ownerId;

	@NotBlank(message = "Owner Name required and should not be blank or empty")
	@ApiModelProperty(value = "ownerName", required = true)
	private String ownerName;
	
	private String contactNumber;
	
	private String alternateContactNumber;
	
	private String emailAddress;
	
	private String address;
	
	private String description;

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
