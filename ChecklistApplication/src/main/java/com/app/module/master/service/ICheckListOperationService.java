package com.app.module.master.service;

import com.app.beans.CheckListOperationBean;
import com.app.beans.ResponseBean;
import com.app.exception.CheckListAppException;

public interface ICheckListOperationService {

	ResponseBean insertOrUpdateCheckListOperation(CheckListOperationBean checkListOperationBean)
			throws CheckListAppException;

	ResponseBean getCheckListOperations() throws CheckListAppException;

	ResponseBean getCheckListOperationByCheckListOperationId(Long checkListOperationId) throws CheckListAppException;

}