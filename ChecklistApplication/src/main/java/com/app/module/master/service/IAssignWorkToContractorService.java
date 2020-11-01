package com.app.module.master.service;

import com.app.beans.AssignWorkToContractorBean;
import com.app.beans.ResponseBean;
import com.app.exception.CheckListAppException;

public interface IAssignWorkToContractorService {

	ResponseBean insertOrUpdateAssignWorkToContractor( AssignWorkToContractorBean assignWorkToContractorBean) throws CheckListAppException;

	ResponseBean getActiveAssignWorkToContractors() throws CheckListAppException;

	ResponseBean getAssignWorkToContractors() throws CheckListAppException;

}
