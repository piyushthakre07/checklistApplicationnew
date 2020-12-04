package com.app.module.master.service;

import java.util.List;

import com.app.beans.AssignWorkToContractorBean;
import com.app.beans.AssignWorkToContractorResponseBean;
import com.app.beans.ResponseBean;
import com.app.exception.CheckListAppException;

public interface IAssignWorkToContractorService {

	ResponseBean insertOrUpdateAssignWorkToContractor( AssignWorkToContractorBean assignWorkToContractorBean) throws CheckListAppException;

	ResponseBean getActiveAssignWorkToContractors() throws CheckListAppException;

	ResponseBean getAssignWorkToContractors() throws CheckListAppException;

	List<AssignWorkToContractorResponseBean> getAssignWorkToContractorByFlatIdNWorkType(Long flatId, Long workTypeId)
			throws CheckListAppException;

}
