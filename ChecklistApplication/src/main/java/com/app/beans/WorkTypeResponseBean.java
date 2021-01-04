package com.app.beans;

import java.util.List;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class WorkTypeResponseBean {

	private Long workTypeId;

	private String workType;

	private String description;

	private boolean active;

	private List<TaskResponseBean> tasks;

}
