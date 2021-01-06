package com.app.beans;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class UserLoginResponseBean {

	private Long userLoginId;

	private String userName;

    private String userType;
	
    private String token;
    
	private boolean active;
	
}
