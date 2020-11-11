package com.app.module.master.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.app.entities.Owner;

/**
 * @author Piyush.Thakre
 *
 */
public interface IOwnerDao extends JpaRepository<Owner, Long> {

	@Query("select owner from Owner owner where active=true")
	List<Owner> getAllActiveOwners();
	
	@Query("select owner from Owner owner where owner.ownerId=?1 ")
	List<Owner> getOwnerByOwnerId(Long ownerId);
}

