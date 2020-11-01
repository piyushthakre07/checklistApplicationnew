package com.app.module.master.service;

import com.app.beans.FlatTypeBean;
import com.app.beans.ResponseBean;
import com.app.exception.CheckListAppException;

public interface IFlatTypeService {

	ResponseBean insertOrUpdateFlatType(FlatTypeBean flatTypeBean) throws CheckListAppException;

	ResponseBean getActiveFlatTypes() throws CheckListAppException;

	ResponseBean getFlatTypes() throws CheckListAppException;
}
