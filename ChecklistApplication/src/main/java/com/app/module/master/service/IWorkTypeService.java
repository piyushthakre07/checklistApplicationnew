package com.app.module.master.service;

import com.app.beans.WorkTypeBean;
import com.app.beans.ResponseBean;
import com.app.exception.CheckListAppException;

public interface IWorkTypeService {

	ResponseBean insertOrUpdateWorkType(WorkTypeBean workTypeBean) throws CheckListAppException;

	ResponseBean getActiveWorkTypes() throws CheckListAppException;

	ResponseBean getWorkTypes() throws CheckListAppException;
}
