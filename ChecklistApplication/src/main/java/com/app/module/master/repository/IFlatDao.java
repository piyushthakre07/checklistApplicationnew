package com.app.module.master.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.app.entities.Flat;

public interface IFlatDao extends JpaRepository<Flat, Long> {

	@Query("select flat from Flat flat where active=true and flat.project.active=true and flat.building.active=true")
	List<Flat> getActiveFlats();

	@Query("select flat from Flat flat where flat.floor.floorId=?1 and active=true and flat.project.active=true and flat.building.active=true and flat.floor.active=true")
	List<Flat> getActiveFlatsByFloorId(Long floorId);

	@Query("select flat from Flat flat where flat.building.buildingId=?1 and active=true and flat.project.active=true and flat.building.active=true and flat.floor.active=true")
	List<Flat> getActiveFlatsByBuildingId(Long buildingId);
	
	@Query("select flat from Flat flat where flat.flatId=?1 ")
	List<Flat> getFlatByFlatId(Long flatId);
	
	@Query("select flat from Flat flat where flat.flatType.flatTypeId=?1 ")
	List<Flat> getFlatByFlatTypeId(Long flatTypeId);
	
	@Query("select flat from Flat flat where flat.flatId IN :flatIds ")
	List<Flat> getFlatByFlatIdList(List<Long> flatIds);
	
	@Query("select flat from Flat flat where flat.flatId IN :flatIds and flat.floor.floorId=:floorId and active=true")
	List<Flat> getFlatByFlatIdList(List<Long> flatIds,Long floorId);
	
	@Query("select flat from Flat flat where flat.flatId IN :flatIds and flat.building.buildingId=:buildingId and active=true")
	List<Flat> prepareFlatBeanForOwnersByBuildingId(List<Long> flatIds,Long buildingId);
	
	

}
