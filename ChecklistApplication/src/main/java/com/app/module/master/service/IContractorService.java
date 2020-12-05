package com.app.module.master.service;

import com.app.beans.ContractorBean;
import com.app.beans.ResponseBean;
import com.app.exception.CheckListAppException;

public interface IContractorService {

	ResponseBean insertOrUpdateContractor(ContractorBean contractorBean) throws CheckListAppException;

	ResponseBean getActiveContractors() throws CheckListAppException;

	ResponseBean getContractors() throws CheckListAppException;

	ResponseBean getContractorsById(Long contractorId) throws CheckListAppException;
}
