package com.app.beans;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class UserLoginBean {

	private Long userLoginId;

	private String userName;

    private String password;

    private String userType;
	
	private boolean active;
	
	private boolean block;
	


}
