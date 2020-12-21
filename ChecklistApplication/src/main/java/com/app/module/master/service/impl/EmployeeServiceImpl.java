package com.app.module.master.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.app.beans.EmployeeBean;
import com.app.beans.ResponseBean;
import com.app.constant.MessageConstant;
import com.app.entities.Employee;
import com.app.entities.UserLogin;
import com.app.exception.CheckListAppException;
import com.app.module.master.repository.IFlatDao;
import com.app.module.master.repository.IUserLoginDao;
import com.app.module.master.repository.IEmployeeDao;
import com.app.module.master.service.IEmployeeService;

/**
 * @author Piyush.Thakre
 * @since 13 sep 2020
 *
 */
@Service
public class EmployeeServiceImpl implements IEmployeeService {

	@Autowired
	IEmployeeDao employeeDao;

	@Autowired
	IFlatDao flatDao;
	
	@Autowired
	IUserLoginDao userLoginDao;

	@Override
	public ResponseBean insertOrUpdateEmployee(EmployeeBean employeeBean) throws CheckListAppException {
		Employee employee = new Employee();
		BeanUtils.copyProperties(employeeBean, employee);
		UserLogin userLogin = new UserLogin();
		BeanUtils.copyProperties(employeeBean.getUserLogin(), userLogin);
		userLoginDao.save(userLogin);
		employee.setUserLogin(userLogin);
		employeeDao.save(employee);
		return ResponseBean.builder().message(MessageConstant.DATA_SAVE_SUCCESS)
				.messageDescription(MessageConstant.OWNER_SAVE_SUCCESS_MESSAGE).status(true)
				.satusCode(HttpStatus.CREATED.value()).hasError(false).build();
	}
	
	@Override
	public ResponseBean approveEmployee(EmployeeBean employeeBean) throws CheckListAppException {
		Employee employee = new Employee();
		BeanUtils.copyProperties(employeeBean, employee);
		employee.setActive(true);
		employeeDao.save(employee);
		return ResponseBean.builder().message(MessageConstant.DATA_SAVE_SUCCESS)
				.messageDescription(MessageConstant.OWNER_APPROVE_SUCCESS_MESSAGE).status(true)
				.satusCode(HttpStatus.CREATED.value()).hasError(false).build();
	}

	@Override
	public ResponseBean getActiveEmployees() throws CheckListAppException {
		try {
			return ResponseBean.builder().data(prepareEmployeesBeanFromEmployees(employeeDao.getAllActiveEmployees())).status(true)
					.hasError(false).message(MessageConstant.SUCCESS_MESSAGE).build();
		} catch (Exception e) {
			throw new CheckListAppException(CheckListAppException.SERVER_ERROR, MessageConstant.SERVER_ERROR_MESSAGE,
					MessageConstant.QUERY_FETCH_EXCPTION);
		}
	}

	@Override
	public ResponseBean getEmployees() throws CheckListAppException {
		try {
			return ResponseBean.builder().data(prepareEmployeesBeanFromEmployees(employeeDao.findAll())).status(true)
					.hasError(false).message(MessageConstant.SUCCESS_MESSAGE).build();
		} catch (Exception e) {
			throw new CheckListAppException(CheckListAppException.SERVER_ERROR, MessageConstant.SERVER_ERROR_MESSAGE,
					MessageConstant.QUERY_FETCH_EXCPTION);
		}
	}
		

	private List<EmployeeBean> prepareEmployeesBeanFromEmployees(List<Employee> allEmployees) {
		List<EmployeeBean> employeeBeans = new ArrayList<EmployeeBean>();
		allEmployees.forEach(employee -> {
			EmployeeBean employeeBean = new EmployeeBean();
			BeanUtils.copyProperties(employee, employeeBean);
			employeeBeans.add(employeeBean);
		});
		return employeeBeans;
	}
}
