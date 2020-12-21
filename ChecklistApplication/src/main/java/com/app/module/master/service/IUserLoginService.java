package com.app.module.master.service;

import com.app.beans.UserLoginBean;
import com.app.entities.UserLogin;

import java.util.List;

import com.app.beans.ResponseBean;
import com.app.exception.CheckListAppException;

/**
 * @author Piyush.Thakre
 *
 */
public interface IUserLoginService {

	ResponseBean getActiveUserLogins() throws CheckListAppException;

	ResponseBean getUserLogins() throws CheckListAppException;
	
	ResponseBean getUserLoginByUserLoginId(Long userLoginId) throws CheckListAppException;

	List<UserLogin> getUserLoginByuserNameAndToken(String userName, String token) throws CheckListAppException;

	ResponseBean login(UserLoginBean userLoginBean) throws CheckListAppException;

}
