package com.app.beans;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class ResponseBean {
	private String message;
	private String messageDescription;
	private Integer satusCode;
	private boolean status;
	private String errorCode;
	private Boolean hasError;
	Object data;

}
