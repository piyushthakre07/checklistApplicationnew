package com.app.module.master.service;

import java.util.List;

import com.app.beans.ApproveOwnerRequestBean;
import com.app.beans.OwnerBean;
import com.app.beans.ResponseBean;
import com.app.exception.CheckListAppException;

public interface IOwnerService {

	ResponseBean insertOrUpdateOwner(OwnerBean ownerBean) throws CheckListAppException;

	ResponseBean getActiveOwners() throws CheckListAppException;

	ResponseBean getOwners() throws CheckListAppException;

	ResponseBean approveOwner(ApproveOwnerRequestBean ownerBean) throws CheckListAppException;

	List<OwnerBean> getOwnersByLoginId(Long userLoginId) throws CheckListAppException;
}
