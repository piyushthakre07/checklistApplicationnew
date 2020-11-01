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
public class TaskBean {

	private Long taskId;

	@NotBlank(message = "Task Name required and should not be blank or empty")
	@ApiModelProperty(value = "task", required = true)
	@Size(min = 3, max = 30, message = "Task name must be between 3 and 30 characters")
	private String task;

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
