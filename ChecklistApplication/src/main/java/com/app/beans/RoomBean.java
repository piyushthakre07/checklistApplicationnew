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
public class RoomBean {

	private Long roomId;

	@NotBlank(message = "Room Name required and should not be blank or empty")
	@ApiModelProperty(value = "roomName", required = true)
	@Size(min = 3, max = 30, message = "Room name must be between 3 and 30 characters")
	private String roomName;

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
