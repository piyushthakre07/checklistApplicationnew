package com.app.beans;

import java.util.List;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class FlatRequestBean {
	
	
	private Long projectId;
	
	private Long buildingId;

	private List<FlatBean> flatBeanList;
}
