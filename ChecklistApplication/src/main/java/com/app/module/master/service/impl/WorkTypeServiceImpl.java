package com.app.module.master.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.app.beans.WorkTypeBean;
import com.app.beans.ResponseBean;
import com.app.constant.MessageConstant;
import com.app.entities.WorkType;
import com.app.exception.CheckListAppException;
import com.app.module.master.repository.IWorkTypeDao;
import com.app.module.master.service.IWorkTypeService;

/**
 * @author Piyush.Thakre
 * @since 13 sep 2020
 *
 */
@Service
public class WorkTypeServiceImpl implements IWorkTypeService {

	@Autowired
	IWorkTypeDao workTypeDao;

	@Override
	public ResponseBean insertOrUpdateWorkType(WorkTypeBean workTypeBean) throws CheckListAppException {
		WorkType workType = new WorkType();
		BeanUtils.copyProperties(workTypeBean, workType);
		workTypeDao.save(workType);
		return ResponseBean.builder().message(MessageConstant.DATA_SAVE_SUCCESS)
				.messageDescription(MessageConstant.WORK_TYPE_SAVE_SUCCESS_MESSAGE).status(true)
				.satusCode(HttpStatus.CREATED.value()).hasError(false).build();
	}

	@Override
	public ResponseBean getActiveWorkTypes() throws CheckListAppException {
		try {
			return ResponseBean.builder().data(prepareWorkTypesBeanFromWorkTypes(workTypeDao.getAllActiveWorkTypes()))
					.status(true).hasError(false).message(MessageConstant.SUCCESS_MESSAGE).build();
		} catch (Exception e) {
			throw new CheckListAppException(CheckListAppException.SERVER_ERROR, MessageConstant.SERVER_ERROR_MESSAGE,
					MessageConstant.QUERY_FETCH_EXCPTION);
		}
	}

	@Override
	public ResponseBean getWorkTypes() throws CheckListAppException {
		try {
			return ResponseBean.builder().data(prepareWorkTypesBeanFromWorkTypes(workTypeDao.findAll())).status(true)
					.hasError(false).message(MessageConstant.SUCCESS_MESSAGE).build();
		} catch (Exception e) {
			throw new CheckListAppException(CheckListAppException.SERVER_ERROR, MessageConstant.SERVER_ERROR_MESSAGE,
					MessageConstant.QUERY_FETCH_EXCPTION);
		}
	}

	private List<WorkTypeBean> prepareWorkTypesBeanFromWorkTypes(List<WorkType> allWorkTypes) {
		List<WorkTypeBean> workTypeBeans = new ArrayList<WorkTypeBean>();
		allWorkTypes.forEach(workType -> {
			WorkTypeBean workTypeBean = new WorkTypeBean();
			BeanUtils.copyProperties(workType, workTypeBean);
			workTypeBeans.add(workTypeBean);
		});
		return workTypeBeans;
	}
}
