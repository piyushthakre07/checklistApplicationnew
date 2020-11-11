package com.app.module.master.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.app.entities.AssignRoomToFlat;

/**
 * @author Piyush.Thakre
 *
 */
public interface IAssignRoomToFlatDao extends JpaRepository<AssignRoomToFlat, Long> {

	@Query("select assignRoomToFlat from AssignRoomToFlat assignRoomToFlat where active=true")
	List<AssignRoomToFlat> getActiveAssignRoomToFlats();

	@Query("select assignRoomToFlat from AssignRoomToFlat assignRoomToFlat where assignRoomToFlat.flat.flatId=?1 and active=true")
	List<AssignRoomToFlat> getAssignRoomToFlatByFlatId(Long flatId);
	
	@Query("select assignRoomToFlat from AssignRoomToFlat assignRoomToFlat where assignRoomToFlat.assignRoomToFlatId=?1 ")
	List<AssignRoomToFlat> getAssignRoomToFlatByAssignRoomToFlatId(Long assignRoomToFlatId);
}
