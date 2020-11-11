package com.app.module.master.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.app.entities.AssignFlatToOwner;

/**
 * @author Piyush.Thakre
 *
 */
public interface IAssignFlatToOwnerDao extends JpaRepository<AssignFlatToOwner, Long> {

	@Query("select assignFlatToOwner from AssignFlatToOwner assignFlatToOwner where active=true")
	List<AssignFlatToOwner> getActiveAssignFlatToOwners();
	
	@Query("select assignFlatToOwner from AssignFlatToOwner assignFlatToOwner where assignFlatToOwner.assignFlatToOwnerId=?1 ")
	List<AssignFlatToOwner> getAssignFlatToOwnerByAssignFlatToOwnerId(Long assignFlatToOwnerId);
}
