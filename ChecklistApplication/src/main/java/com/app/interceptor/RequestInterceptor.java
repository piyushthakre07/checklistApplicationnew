package com.app.interceptor;


import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import com.app.constant.GenericConstant;
import com.app.constant.MessageConstant;
import com.app.entities.UserLogin;
import com.app.exception.CheckListAppException;
import com.app.module.master.service.IUserLoginService;


@Component
public class RequestInterceptor implements HandlerInterceptor {
	
	
	@Autowired
	IUserLoginService userLoginService;
	
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		String token = request.getHeader(GenericConstant.TOKEN);
		String userName = request.getHeader(GenericConstant.USERNAME);

            if (StringUtils.isBlank(token) || StringUtils.isBlank(userName))
			throw new CheckListAppException(HttpStatus.BAD_REQUEST.value(), HttpStatus.UNAUTHORIZED.getReasonPhrase(), MessageConstant.UNAUTHORIZED);
		
            List<UserLogin> userLogins= userLoginService.getUserLoginByuserNameAndToken(userName, token);
            if(userLogins==null || userLogins.isEmpty()) {
            	throw new CheckListAppException(HttpStatus.BAD_REQUEST.value(), HttpStatus.UNAUTHORIZED.getReasonPhrase(), MessageConstant.UNAUTHORIZED);
        	}
		return HandlerInterceptor.super.preHandle(request, response, handler);
	}

}