package com.app.module.master.service;

import com.app.beans.BuildingBean;
import com.app.beans.BuildingRequestBean;
import com.app.beans.ResponseBean;
import com.app.exception.CheckListAppException;

/**
 * @author Piyush.Thakre
 *
 */
public interface IBuildingService {

	ResponseBean insertOrUpdateBuilding(BuildingBean buildingBean) throws CheckListAppException;

	ResponseBean getActiveBuildings() throws CheckListAppException;

	ResponseBean insertMultipleBuilding(BuildingRequestBean buildingRequestBean) throws CheckListAppException;

	ResponseBean getBuildings() throws CheckListAppException;

	ResponseBean getActiveBuildingsByProjectId(Long projectId) throws CheckListAppException;

	ResponseBean getBuildingByBuildingId(Long buildingId) throws CheckListAppException;

}
