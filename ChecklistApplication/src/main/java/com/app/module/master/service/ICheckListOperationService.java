package com.app.module.master.service;

import java.util.List;

import com.app.beans.CheckListOperationBean;
import com.app.beans.CheckListOperationDefectRequestBean;
import com.app.beans.ResponseBean;
import com.app.exception.CheckListAppException;

public interface ICheckListOperationService {


	ResponseBean getCheckListOperations() throws CheckListAppException;

	ResponseBean getCheckListOperationByCheckListOperationId(Long checkListOperationId) throws CheckListAppException;

	ResponseBean getCheckListOperationReport(CheckListOperationBean checkListOperationBean)
			throws CheckListAppException;

	
	ResponseBean getCheckListOperationTaskDetailsByFlatIdAndAndWorTypeAndTaskIdAndRoomId(Long flatId, Long workTypeId,Long taskId) throws CheckListAppException;

	ResponseBean insertOrUpdateCheckListOperationDefect(CheckListOperationDefectRequestBean checkListOperationDefectRequestBean) throws CheckListAppException;

	ResponseBean insertOrUpdateCheckListOperation(CheckListOperationBean checkListOperationBean)
			throws CheckListAppException;

	 List<byte[]> getDefectCheckListOperationByFlatIdAndWorTypeAndTaskIdAndRoomId(Long flatId, Long workTypeId,
			Long taskId, Long roomId) throws CheckListAppException;

}
