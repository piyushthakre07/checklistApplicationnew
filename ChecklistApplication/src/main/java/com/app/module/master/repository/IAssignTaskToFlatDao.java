package com.app.module.master.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.app.entities.AssignTaskToFlat;

/**
 * @author Piyush.Thakre
 *
 */
public interface IAssignTaskToFlatDao extends JpaRepository<AssignTaskToFlat, Long> {

	@Query("select assignTaskToFlat from AssignTaskToFlat assignTaskToFlat where active=true")
	List<AssignTaskToFlat> getActiveAssignTaskToFlats();
}
