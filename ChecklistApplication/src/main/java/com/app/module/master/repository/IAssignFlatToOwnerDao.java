package com.app.module.master.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.app.entities.AssignFlatToOwner;
import com.app.entities.Owner;

/**
 * @author Piyush.Thakre
 *
 */
public interface IAssignFlatToOwnerDao extends JpaRepository<AssignFlatToOwner, Long> {

	@Query("select assignFlatToOwner from AssignFlatToOwner assignFlatToOwner where active=true")
	List<AssignFlatToOwner> getActiveAssignFlatToOwners();
	
	@Query("select assignFlatToOwner from AssignFlatToOwner assignFlatToOwner where assignFlatToOwner.assignFlatToOwnerId=?1 ")
	List<AssignFlatToOwner> getAssignFlatToOwnerByAssignFlatToOwnerId(Long assignFlatToOwnerId);
	
	@Query("select assignFlatToOwner.owner from AssignFlatToOwner assignFlatToOwner where assignFlatToOwner.flat.flatId=?1 ")
	List<Owner> getOwnersByFlatId(Long flatId);
	
	@Query("select assignFlatToOwner.flat.project.projectId from AssignFlatToOwner assignFlatToOwner where assignFlatToOwner.owner.ownerId=?1 ")
	List<Long> getFlatByOwnerId(Long ownerId);
	
	@Query("select assignFlatToOwner.flat.building.buildingId from AssignFlatToOwner assignFlatToOwner where assignFlatToOwner.owner.ownerId=?1 ")
	List<Long> getBuildingIdByOwnerId(Long ownerId);
	
	@Query("select assignFlatToOwner.flat.flatId from AssignFlatToOwner assignFlatToOwner where assignFlatToOwner.owner.ownerId=?1 ")
	List<Long> getFlatIdsByOwnerId(Long ownerId);
	
	@Query("select assignFlatToOwner.flat.floor.floorId from AssignFlatToOwner assignFlatToOwner where assignFlatToOwner.owner.ownerId=?1 ")
	List<Long> getFloorIdByOwnerId(Long ownerId);

}
