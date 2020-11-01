package com.app.module.master.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.app.beans.RoomBean;
import com.app.beans.ResponseBean;
import com.app.constant.MessageConstant;
import com.app.entities.Room;
import com.app.exception.CheckListAppException;
import com.app.module.master.repository.IRoomDao;
import com.app.module.master.service.IRoomService;

/**
 * @author Piyush.Thakre
 * @since 13 sep 2020
 *
 */
@Service
public class RoomServiceImpl implements IRoomService {

	@Autowired
	IRoomDao roomDao;

	@Override
	public ResponseBean insertOrUpdateRoom(RoomBean roomBean) throws CheckListAppException {
		Room room = new Room();
		BeanUtils.copyProperties(roomBean, room);
		roomDao.save(room);
		return ResponseBean.builder().message(MessageConstant.DATA_SAVE_SUCCESS)
				.messageDescription(MessageConstant.ROOM_SAVE_SUCCESS_MESSAGE).status(true)
				.satusCode(HttpStatus.CREATED.value()).hasError(false).build();
	}

	@Override
	public ResponseBean getActiveRooms() throws CheckListAppException {
		try {
			return ResponseBean.builder().data(prepareRoomsBeanFromRooms(roomDao.getAllActiveRooms())).status(true)
					.hasError(false).message(MessageConstant.SUCCESS_MESSAGE).build();
		} catch (Exception e) {
			throw new CheckListAppException(CheckListAppException.SERVER_ERROR, MessageConstant.SERVER_ERROR_MESSAGE,
					MessageConstant.QUERY_FETCH_EXCPTION);
		}
	}

	@Override
	public ResponseBean getRooms() throws CheckListAppException {
		try {
			return ResponseBean.builder().data(prepareRoomsBeanFromRooms(roomDao.findAll())).status(true)
					.hasError(false).message(MessageConstant.SUCCESS_MESSAGE).build();
		} catch (Exception e) {
			throw new CheckListAppException(CheckListAppException.SERVER_ERROR, MessageConstant.SERVER_ERROR_MESSAGE,
					MessageConstant.QUERY_FETCH_EXCPTION);
		}
	}

	private List<RoomBean> prepareRoomsBeanFromRooms(List<Room> allRooms) {
		List<RoomBean> roomBeans = new ArrayList<RoomBean>();
		allRooms.forEach(room -> {
			RoomBean roomBean = new RoomBean();
			BeanUtils.copyProperties(room, roomBean);
			roomBeans.add(roomBean);
		});
		return roomBeans;
	}
}
