package com.app.module.master.service;

import com.app.beans.RoomBean;
import com.app.beans.ResponseBean;
import com.app.exception.CheckListAppException;

public interface IRoomService {
	ResponseBean insertOrUpdateRoom(RoomBean roomBean) throws CheckListAppException;

	ResponseBean getActiveRooms() throws CheckListAppException;

	ResponseBean getRooms() throws CheckListAppException;
}
