package com.app.module.master.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.app.beans.AssignRoomToFlatBean;
import com.app.beans.AssignRoomToFlatResponseBean;
import com.app.beans.BuildingBean;
import com.app.beans.FlatBean;
import com.app.beans.FlatTypeBean;
import com.app.beans.ProjectBean;
import com.app.beans.ResponseBean;
import com.app.beans.RoomBean;
import com.app.constant.MessageConstant;
import com.app.entities.AssignRoomToFlat;
import com.app.entities.Building;
import com.app.entities.Flat;
import com.app.entities.FlatType;
import com.app.entities.Project;
import com.app.entities.Room;
import com.app.exception.CheckListAppException;
import com.app.module.master.repository.IAssignRoomToFlatDao;
import com.app.module.master.repository.IFlatDao;
import com.app.module.master.service.IAssignRoomToFlatService;

/**
 * @author Piyush.Thakre
 *
 */
@Service
public class AssignRoomToFlatServiceImpl implements IAssignRoomToFlatService {

	/*
	 * @Autowired IAssignRoomToFlatValidation assignRoomToFlatValidation;
	 */

	@Autowired
	IAssignRoomToFlatDao assignRoomToFlatDao;
	
	@Autowired
	IFlatDao flatDao;

	@Override
	public ResponseBean insertOrUpdateAssignRoomToFlat(AssignRoomToFlatBean assignRoomToFlatBean)
			throws CheckListAppException {
		// assignRoomToFlatValidation.checkDuplicateAssignRoomToFlat(assignRoomToFlatBean);
		AssignRoomToFlat assignRoomToFlat = new AssignRoomToFlat();
		BeanUtils.copyProperties(assignRoomToFlatBean, assignRoomToFlat);
		Project project = new Project();
		project.setProjectId(assignRoomToFlatBean.getProjectId());
		assignRoomToFlat.setProject(project);
		Building building = new Building();
		building.setBuildingId(assignRoomToFlatBean.getBuildingId());
		assignRoomToFlat.setBuilding(building);

		FlatType flatType = new FlatType();
		flatType.setFlatTypeId(assignRoomToFlatBean.getFlatTypeId());
		assignRoomToFlat.setFlatType(flatType);
		List<RoomBean> rooms = assignRoomToFlatBean.getRooms();
		if (assignRoomToFlatBean.getFlatId() != 0) {
			Flat flat = new Flat();
			flat.setFlatId(assignRoomToFlatBean.getFlatId());
			assignRoomToFlat.setFlat(flat);
			rooms.forEach(roomBean -> {
				Room room = new Room();
				BeanUtils.copyProperties(roomBean, room);
				assignRoomToFlat.setRoom(room);
				assignRoomToFlatDao.save(assignRoomToFlat);
			});
		} else {
			List<Flat> flats = flatDao.getFlatByFlatTypeId(assignRoomToFlatBean.getFlatTypeId());
			flats.stream().forEach(flat -> {
				assignRoomToFlat.setFlat(flat);
				rooms.forEach(roomBean -> {
					Room room = new Room();
					BeanUtils.copyProperties(roomBean, room);
					assignRoomToFlat.setRoom(room);
					assignRoomToFlatDao.save(assignRoomToFlat);
				});
			});
		}
		return ResponseBean.builder().message(MessageConstant.DATA_SAVE_SUCCESS)
				.messageDescription(MessageConstant.ASSIGN_ROOM_TO_FLAT).status(true)
				.satusCode(HttpStatus.CREATED.value()).hasError(false).build();
	}

	@Override
	public ResponseBean getActiveAssignRoomToFlats() throws CheckListAppException {
		try {
			return ResponseBean.builder()
					.data(prepareAssignRoomToFlatBeansFromAssignRoomToFlat(
							assignRoomToFlatDao.getActiveAssignRoomToFlats()))
					.status(true).hasError(false).message(MessageConstant.SUCCESS_MESSAGE).build();
		} catch (Exception e) {
			throw new CheckListAppException(CheckListAppException.SERVER_ERROR, MessageConstant.SERVER_ERROR_MESSAGE,
					MessageConstant.QUERY_FETCH_EXCPTION);
		}
	}

	@Override
	public ResponseBean getAssignRoomToFlats() throws CheckListAppException {
		try {
			return ResponseBean.builder()
					.data(prepareAssignRoomToFlatBeansFromAssignRoomToFlat(assignRoomToFlatDao.findAll()))
					.status(true).hasError(false).message(MessageConstant.SUCCESS_MESSAGE).build();
		} catch (Exception e) {
			throw new CheckListAppException(CheckListAppException.SERVER_ERROR, MessageConstant.SERVER_ERROR_MESSAGE,
					MessageConstant.QUERY_FETCH_EXCPTION);
		}
	}

	private List<AssignRoomToFlatResponseBean> prepareAssignRoomToFlatBeansFromAssignRoomToFlat(
			List<AssignRoomToFlat> allAssignRoomToFlats) {
		List<AssignRoomToFlatResponseBean> assignRoomToFlatBeans = new ArrayList<AssignRoomToFlatResponseBean>();
		allAssignRoomToFlats.forEach(assignRoomToFlat -> {
			AssignRoomToFlatResponseBean assignRoomToFlatResponseBean = new AssignRoomToFlatResponseBean();
			BeanUtils.copyProperties(assignRoomToFlat, assignRoomToFlatResponseBean);
			ProjectBean projectBean = new ProjectBean();
			BeanUtils.copyProperties(assignRoomToFlat.getProject(), projectBean);
			assignRoomToFlatResponseBean.setProject(projectBean);
			BuildingBean buildingBean = new BuildingBean();
			BeanUtils.copyProperties(assignRoomToFlat.getBuilding(), buildingBean);
			assignRoomToFlatResponseBean.setBuilding(buildingBean);
			
			FlatTypeBean flatTypeBean=new FlatTypeBean();
			BeanUtils.copyProperties(assignRoomToFlat.getFlatType(), flatTypeBean);
			assignRoomToFlatResponseBean.setFlatType(flatTypeBean);
			
			FlatBean flatBean = new FlatBean();
			BeanUtils.copyProperties(assignRoomToFlat.getFlat(), flatBean);
			assignRoomToFlatResponseBean.setFlat(flatBean);
			assignRoomToFlatBeans.add(assignRoomToFlatResponseBean);
		});
		return assignRoomToFlatBeans;
	}
}
