package com.app.module.master.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.app.beans.FlatTypeBean;
import com.app.beans.ResponseBean;
import com.app.constant.MessageConstant;
import com.app.entities.FlatType;
import com.app.exception.CheckListAppException;
import com.app.module.master.repository.IFlatTypeDao;
import com.app.module.master.service.IFlatTypeService;

/**
 * @author Piyush.Thakre
 * @since 13 sep 2020
 *
 */
@Service
public class FlatTypeServiceImpl implements IFlatTypeService {

	@Autowired
	IFlatTypeDao flatTypeDao;

	@Override
	public ResponseBean insertOrUpdateFlatType(FlatTypeBean flatTypeBean) throws CheckListAppException {
		FlatType flatType = new FlatType();
		BeanUtils.copyProperties(flatTypeBean, flatType);
		flatTypeDao.save(flatType);
		return ResponseBean.builder().message(MessageConstant.DATA_SAVE_SUCCESS)
				.messageDescription(MessageConstant.FLAT_TYPE_SAVE_SUCCESS_MESSAGE).status(true)
				.satusCode(HttpStatus.CREATED.value()).hasError(false).build();
	}

	@Override
	public ResponseBean getActiveFlatTypes() throws CheckListAppException {
		try {
			return ResponseBean.builder().data(prepareFlatTypesBeanFromFlatTypes(flatTypeDao.getAllActiveFlatTypes()))
					.status(true).hasError(false).message(MessageConstant.SUCCESS_MESSAGE).build();
		} catch (Exception e) {
			throw new CheckListAppException(CheckListAppException.SERVER_ERROR, MessageConstant.SERVER_ERROR_MESSAGE,
					MessageConstant.QUERY_FETCH_EXCPTION);
		}
	}

	@Override
	public ResponseBean getFlatTypes() throws CheckListAppException {
		try {
			return ResponseBean.builder().data(prepareFlatTypesBeanFromFlatTypes(flatTypeDao.findAll())).status(true)
					.hasError(false).message(MessageConstant.SUCCESS_MESSAGE).build();
		} catch (Exception e) {
			throw new CheckListAppException(CheckListAppException.SERVER_ERROR, MessageConstant.SERVER_ERROR_MESSAGE,
					MessageConstant.QUERY_FETCH_EXCPTION);
		}
	}

	private List<FlatTypeBean> prepareFlatTypesBeanFromFlatTypes(List<FlatType> allFlatTypes) {
		List<FlatTypeBean> flatTypeBeans = new ArrayList<FlatTypeBean>();
		allFlatTypes.forEach(flatType -> {
			FlatTypeBean flatTypeBean = new FlatTypeBean();
			BeanUtils.copyProperties(flatType, flatTypeBean);
			flatTypeBeans.add(flatTypeBean);
		});
		return flatTypeBeans;
	}
}
