package com.app.module.master.service;

import com.app.beans.AproveEmployeeRequestBean;
import com.app.beans.EmployeeBean;
import com.app.beans.ResponseBean;
import com.app.exception.CheckListAppException;

public interface IEmployeeService {

	ResponseBean insertOrUpdateEmployee(EmployeeBean employeeBean) throws CheckListAppException;

	ResponseBean getActiveEmployees() throws CheckListAppException;

	ResponseBean getEmployees() throws CheckListAppException;

	ResponseBean approveEmployee(AproveEmployeeRequestBean employeeBean) throws CheckListAppException;
}
