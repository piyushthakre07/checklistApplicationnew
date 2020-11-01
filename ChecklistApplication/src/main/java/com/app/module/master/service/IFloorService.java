package com.app.module.master.service;

import com.app.beans.FloorBean;
import com.app.beans.FloorRequestBean;
import com.app.beans.ResponseBean;
import com.app.exception.CheckListAppException;

public interface IFloorService {

	ResponseBean insertOrUpdateFloor( FloorBean floorBean) throws CheckListAppException;

	ResponseBean insertMultipleFloor(FloorRequestBean floorRequestBean) throws CheckListAppException;

	ResponseBean getActiveFloors() throws CheckListAppException;
	
	ResponseBean getFloors() throws CheckListAppException;

	ResponseBean getActiveFloorsBybuildingId(Long buildingId) throws CheckListAppException;
	
	ResponseBean getFloorByFloorId(Long floorId) throws CheckListAppException;

}
