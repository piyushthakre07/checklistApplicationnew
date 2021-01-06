package com.app.module.master.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.app.beans.ResponseBean;
import com.app.beans.UserLoginBean;
import com.app.beans.UserLoginResponseBean;
import com.app.constant.MessageConstant;
import com.app.entities.UserLogin;
import com.app.exception.CheckListAppException;
import com.app.module.master.repository.IUserLoginDao;
import com.app.module.master.service.IUserLoginService;


/**
 * @author Piyush.Thakre
 * @since 13 sep 2020
 *
 */
@Service
public class UserLoginServiceImpl implements IUserLoginService {

	private static final String ALPHA_NUMERIC_STRING = "ABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";

	@Autowired
	IUserLoginDao UserLoginDao;

	@Override
	public ResponseBean getActiveUserLogins() throws CheckListAppException {
		try {
			return ResponseBean.builder().data(prepareUserLoginsBeanFromUserLogins(UserLoginDao.getAllActiveUserLogins()))
					.status(true).hasError(false).message(MessageConstant.SUCCESS_MESSAGE).build();
		} catch (Exception e) {
			throw new CheckListAppException(CheckListAppException.SERVER_ERROR, MessageConstant.SERVER_ERROR_MESSAGE,
					MessageConstant.QUERY_FETCH_EXCPTION);
		}
	}

	@Override
	public ResponseBean getUserLogins() throws CheckListAppException {
		try {
			return ResponseBean.builder().data(prepareUserLoginsBeanFromUserLogins(UserLoginDao.findAll())).status(true)
					.hasError(false).message(MessageConstant.SUCCESS_MESSAGE).build();
		} catch (Exception e) {
			throw new CheckListAppException(CheckListAppException.SERVER_ERROR, MessageConstant.SERVER_ERROR_MESSAGE,
					MessageConstant.QUERY_FETCH_EXCPTION);
		}
	}

	@Override
	public ResponseBean getUserLoginByUserLoginId(Long UserLoginId) throws CheckListAppException {
		try {
			return ResponseBean.builder()
					.data(prepareUserLoginsBeanFromUserLogins(UserLoginDao.getUserLoginByUserLoginId(UserLoginId))).status(true)
					.hasError(false).message(MessageConstant.SUCCESS_MESSAGE).build();
		} catch (Exception e) {
			throw new CheckListAppException(CheckListAppException.SERVER_ERROR, MessageConstant.SERVER_ERROR_MESSAGE,
					MessageConstant.QUERY_FETCH_EXCPTION);
		}
	}
	
	@Override
	public List<UserLogin> getUserLoginByuserNameAndToken(String userName,String token) throws CheckListAppException {
		try {
			return UserLoginDao.getUserLoginByuserNameAndToken(userName, token);
		} catch (Exception e) {
			throw new CheckListAppException(CheckListAppException.SERVER_ERROR, MessageConstant.SERVER_ERROR_MESSAGE,
					MessageConstant.QUERY_FETCH_EXCPTION);
		}
	}
	
	@Override
	public ResponseBean login(UserLoginBean userLoginBean) throws CheckListAppException {
		try {
			UserLogin userLogin=UserLoginDao.getUserLoginByuserNameAndPassword(userLoginBean.getUserName(), userLoginBean.getPassword());
			String token = randomAlphaNumeric(30);
			userLogin.setToken(token);
			UserLoginDao.save(userLogin);
			UserLoginResponseBean userLoginResponseBean=new UserLoginResponseBean();
			BeanUtils.copyProperties(userLogin, userLoginResponseBean);
			return ResponseBean.builder()
					.data(userLoginResponseBean).status(true)
					.hasError(false).message(MessageConstant.SUCCESS_MESSAGE).build();
		} catch (Exception e) {
			throw new CheckListAppException(CheckListAppException.SERVER_ERROR, MessageConstant.SERVER_ERROR_MESSAGE,
					MessageConstant.QUERY_FETCH_EXCPTION);
		}
	}
	
	
	private List<UserLoginBean> prepareUserLoginsBeanFromUserLogins(List<UserLogin> allUserLogins) {
		List<UserLoginBean> UserLoginBeans = new ArrayList<UserLoginBean>();
		allUserLogins.forEach(UserLogin -> {
			UserLoginBean UserLoginBean = new UserLoginBean();
			BeanUtils.copyProperties(UserLogin, UserLoginBean);
			UserLoginBeans.add(UserLoginBean);
		});
		return UserLoginBeans;
	}
	
	/*
	 * private UserLoginBean prepareUserLoginsBeanFromUserLogin(UserLogin userLogin)
	 * { UserLoginBean UserLoginBean = new UserLoginBean();
	 * BeanUtils.copyProperties(userLogin, UserLoginBean); return UserLoginBean; }
	 */
	

	public static String randomAlphaNumeric(int count) {
	StringBuilder builder = new StringBuilder();
	while (count-- != 0) {
	int character = (int)(Math.random()*ALPHA_NUMERIC_STRING.length());
	builder.append(ALPHA_NUMERIC_STRING.charAt(character));
	}
	return builder.toString();
	}
}
