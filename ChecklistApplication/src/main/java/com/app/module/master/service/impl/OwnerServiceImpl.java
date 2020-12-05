package com.app.module.master.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.app.beans.OwnerBean;
import com.app.beans.ResponseBean;
import com.app.constant.MessageConstant;
import com.app.entities.Owner;
import com.app.exception.CheckListAppException;
import com.app.module.master.repository.IFlatDao;
import com.app.module.master.repository.IOwnerDao;
import com.app.module.master.service.IOwnerService;

/**
 * @author Piyush.Thakre
 * @since 13 sep 2020
 *
 */
@Service
public class OwnerServiceImpl implements IOwnerService {

	@Autowired
	IOwnerDao ownerDao;

	@Autowired
	IFlatDao flatDao;

	@Override
	public ResponseBean insertOrUpdateOwner(OwnerBean ownerBean) throws CheckListAppException {
		Owner owner = new Owner();
		BeanUtils.copyProperties(ownerBean, owner);
		ownerDao.save(owner);
		return ResponseBean.builder().message(MessageConstant.DATA_SAVE_SUCCESS)
				.messageDescription(MessageConstant.OWNER_SAVE_SUCCESS_MESSAGE).status(true)
				.satusCode(HttpStatus.CREATED.value()).hasError(false).build();
	}
	
	@Override
	public ResponseBean approveOwner(OwnerBean ownerBean) throws CheckListAppException {
		Owner owner = new Owner();
		BeanUtils.copyProperties(ownerBean, owner);
		owner.setActive(true);
		ownerDao.save(owner);
		return ResponseBean.builder().message(MessageConstant.DATA_SAVE_SUCCESS)
				.messageDescription(MessageConstant.OWNER_APPROVE_SUCCESS_MESSAGE).status(true)
				.satusCode(HttpStatus.CREATED.value()).hasError(false).build();
	}

	@Override
	public ResponseBean getActiveOwners() throws CheckListAppException {
		try {
			return ResponseBean.builder().data(prepareOwnersBeanFromOwners(ownerDao.getAllActiveOwners())).status(true)
					.hasError(false).message(MessageConstant.SUCCESS_MESSAGE).build();
		} catch (Exception e) {
			throw new CheckListAppException(CheckListAppException.SERVER_ERROR, MessageConstant.SERVER_ERROR_MESSAGE,
					MessageConstant.QUERY_FETCH_EXCPTION);
		}
	}

	@Override
	public ResponseBean getOwners() throws CheckListAppException {
		try {
			return ResponseBean.builder().data(prepareOwnersBeanFromOwners(ownerDao.findAll())).status(true)
					.hasError(false).message(MessageConstant.SUCCESS_MESSAGE).build();
		} catch (Exception e) {
			throw new CheckListAppException(CheckListAppException.SERVER_ERROR, MessageConstant.SERVER_ERROR_MESSAGE,
					MessageConstant.QUERY_FETCH_EXCPTION);
		}
	}
		

	private List<OwnerBean> prepareOwnersBeanFromOwners(List<Owner> allOwners) {
		List<OwnerBean> ownerBeans = new ArrayList<OwnerBean>();
		allOwners.forEach(owner -> {
			OwnerBean ownerBean = new OwnerBean();
			BeanUtils.copyProperties(owner, ownerBean);
			ownerBeans.add(ownerBean);
		});
		return ownerBeans;
	}
}
