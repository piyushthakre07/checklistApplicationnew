package com.app.beans;

import java.util.List;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class BuildingRequestBean {
	
	
	private Long projectId;

	private List<BuildingBean> BuildingBeanList;
}
