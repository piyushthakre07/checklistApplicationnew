package com.app.module.master.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.app.beans.ContractorBean;
import com.app.beans.ResponseBean;
import com.app.constant.MessageConstant;
import com.app.entities.Contractor;
import com.app.exception.CheckListAppException;
import com.app.module.master.repository.IContractorDao;
import com.app.module.master.service.IContractorService;

/**
 * @author Piyush.Thakre
 * @since 13 sep 2020
 *
 */
@Service
public class ContractorServiceImpl implements IContractorService {

	@Autowired
	IContractorDao contractorDao;

	@Override
	public ResponseBean insertOrUpdateContractor(ContractorBean contractorBean) throws CheckListAppException {
		Contractor contractor = new Contractor();
		BeanUtils.copyProperties(contractorBean, contractor);
		contractorDao.save(contractor);
		return ResponseBean.builder().message(MessageConstant.DATA_SAVE_SUCCESS)
				.messageDescription(MessageConstant.CONTRACTOR_SAVE_SUCCESS_MESSAGE).status(true)
				.satusCode(HttpStatus.CREATED.value()).hasError(false).build();
	}

	@Override
	public ResponseBean getActiveContractors() throws CheckListAppException {
		try {
			return ResponseBean.builder()
					.data(prepareContractorsBeanFromContractors(contractorDao.getAllActiveContractors())).status(true)
					.hasError(false).message(MessageConstant.SUCCESS_MESSAGE).build();
		} catch (Exception e) {
			throw new CheckListAppException(CheckListAppException.SERVER_ERROR, MessageConstant.SERVER_ERROR_MESSAGE,
					MessageConstant.QUERY_FETCH_EXCPTION);
		}
	}

	@Override
	public ResponseBean getContractors() throws CheckListAppException {
		try {
			return ResponseBean.builder().data(prepareContractorsBeanFromContractors(contractorDao.findAll()))
					.status(true).hasError(false).message(MessageConstant.SUCCESS_MESSAGE).build();
		} catch (Exception e) {
			throw new CheckListAppException(CheckListAppException.SERVER_ERROR, MessageConstant.SERVER_ERROR_MESSAGE,
					MessageConstant.QUERY_FETCH_EXCPTION);
		}
	}

	private List<ContractorBean> prepareContractorsBeanFromContractors(List<Contractor> allContractors) {
		List<ContractorBean> contractorBeans = new ArrayList<ContractorBean>();
		allContractors.forEach(contractor -> {
			ContractorBean contractorBean = new ContractorBean();
			BeanUtils.copyProperties(contractor, contractorBean);
			contractorBeans.add(contractorBean);
		});
		return contractorBeans;
	}
}
