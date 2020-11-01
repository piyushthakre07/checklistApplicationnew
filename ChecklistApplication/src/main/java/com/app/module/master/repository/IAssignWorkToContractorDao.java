package com.app.module.master.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.app.entities.AssignWorkToContractor;

/**
 * @author Piyush.Thakre
 *
 */
public interface IAssignWorkToContractorDao extends JpaRepository<AssignWorkToContractor, Long> {

	@Query("select assignWorkToContractor from AssignWorkToContractor assignWorkToContractor where active=true")
	List<AssignWorkToContractor> getActiveAssignWorkToContractors();
}
