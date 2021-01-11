package com.app.module.master.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.app.entities.Floor;

/**
 * @author Piyush.Thakre
 *
 */
public interface IFloorDao extends JpaRepository<Floor, Long> {

	@Query("select floor from Floor floor where active=true")
	List<Floor> getActiveFloors();

	@Query("select floor from Floor floor where active=true and floor.project.active=true and floor.building.active=true and floor.building.buildingId=?1")
	List<Floor> getActiveFloorsBybuildingId(Long buildingId);

	@Query("select floor from Floor floor where floor.floorId=?1 ")
	List<Floor> getFloorByFloorId(Long floorId);

	@Query("select floor from Floor floor where floor.floorId IN :floorIds ")
	List<Floor> getFloorsByFloorId(List<Long> floorIds);

	@Query("select floor from Floor floor where floor.floorId IN :floorIds and floor.building.buildingId=:buildingId ")
	List<Floor> getFloorsByFloorIdAndBuildingId(List<Long> floorIds, Long buildingId);
}
