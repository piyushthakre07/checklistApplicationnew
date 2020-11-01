package com.app.module.master.service;

import javax.validation.Valid;

import com.app.beans.FlatBean;
import com.app.beans.FlatRequestBean;
import com.app.beans.ResponseBean;
import com.app.exception.CheckListAppException;

public interface IFlatService {

	ResponseBean insertOrUpdateFlat( FlatBean flatBean) throws CheckListAppException;

	ResponseBean getActiveFlats() throws CheckListAppException;

	ResponseBean insertMultipleFlat(@Valid FlatRequestBean flatRequestBean) throws CheckListAppException;

	ResponseBean getFlats() throws CheckListAppException;

	ResponseBean getActiveFlatsByFloorId(Long floorId) throws CheckListAppException;

	ResponseBean getActiveFlatsByBuildingId(Long buildingId) throws CheckListAppException;

	ResponseBean getFlatByFlatId(Long flatId) throws CheckListAppException;

}
