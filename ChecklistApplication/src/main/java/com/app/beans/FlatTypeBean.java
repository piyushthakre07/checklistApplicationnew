package com.app.beans;

import java.util.Date;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonIgnore;

import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class FlatTypeBean {

	private Long flatTypeId;

	@NotBlank(message = "FlatType Name required and should not be blank or empty")
	@ApiModelProperty(value = "flatType", required = true)
	private String flatType;

	private String description;

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
