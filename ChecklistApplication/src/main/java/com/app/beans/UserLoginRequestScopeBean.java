package com.app.beans;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class UserLoginRequestScopeBean {

	private Long userLoginId;

	private String userName;
	
    private String userType;
	
    private String token;
    
	private boolean active;
	
	private boolean block;
	
	 private OwnerBean owner;

}
