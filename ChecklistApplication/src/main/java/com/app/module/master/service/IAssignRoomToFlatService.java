package com.app.module.master.service;

import com.app.beans.AssignRoomToFlatBean;
import com.app.beans.ResponseBean;
import com.app.exception.CheckListAppException;

public interface IAssignRoomToFlatService {

	ResponseBean insertOrUpdateAssignRoomToFlat( AssignRoomToFlatBean assignRoomToFlatBean) throws CheckListAppException;

	ResponseBean getActiveAssignRoomToFlats() throws CheckListAppException;

	ResponseBean getAssignRoomToFlats() throws CheckListAppException;

	ResponseBean getRoomByFlatId(Long flatId) throws CheckListAppException;

}
