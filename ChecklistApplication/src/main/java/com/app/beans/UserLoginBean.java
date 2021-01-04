package com.app.beans;

import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class UserLoginBean {

	private Long userLoginId;

	private String userName;

    private String password;

    private String userType;
	
    @ApiModelProperty(hidden = true)
    private String token;
    
	private boolean active;
	
	@ApiModelProperty(hidden = true)
	private boolean block;
	


}
