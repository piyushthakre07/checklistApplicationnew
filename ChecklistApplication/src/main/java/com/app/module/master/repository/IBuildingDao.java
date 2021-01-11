package com.app.module.master.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.app.entities.Building;

/**
 * @author Piyush.Thakre
 *
 */
public interface IBuildingDao  extends JpaRepository<Building, Long>{

	@Query("select building from Building building where active=true and building.project.active=true ")
	List<Building> getActiveBuildings();
	
	@Query("select building from Building building where active=true and building.project.projectId=?1 ")
	List<Building> getActiveBuildingsByProjectId(Long projectId);
	
	@Query("select building from Building building where building.buildingId=?1 ")
	List<Building> getBuildingByBuildingId(Long buildingId);
	
	@Query("select building from Building building where building.buildingId IN :buildingIds ")
	List<Building> getBuildingByBuildingId(List<Long> buildingIds);
	
	@Query("select building from Building building where building.buildingId IN :buildingIds and active=true and building.project.projectId=:projectId")
	List<Building> getBuildingByBuildingId(List<Long> buildingIds,Long projectId);
}
