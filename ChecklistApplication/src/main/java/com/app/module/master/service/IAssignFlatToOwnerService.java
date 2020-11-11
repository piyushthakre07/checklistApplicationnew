package com.app.module.master.service;

import com.app.beans.AssignFlatToOwnerBean;
import com.app.beans.ResponseBean;
import com.app.exception.CheckListAppException;

public interface IAssignFlatToOwnerService {

	ResponseBean insertOrUpdateAssignFlatToOwner( AssignFlatToOwnerBean assignFlatToOwnerBean) throws CheckListAppException;

	ResponseBean getActiveAssignFlatToOwners() throws CheckListAppException;

	ResponseBean getAssignFlatToOwners() throws CheckListAppException;

	ResponseBean getAssignFlatToOwnerByAssignFlatToOwnerId(Long assignFlatToOwnerId) throws CheckListAppException;

}
