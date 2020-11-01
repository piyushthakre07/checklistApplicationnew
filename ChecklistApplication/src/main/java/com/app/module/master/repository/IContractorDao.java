package com.app.module.master.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.app.entities.Contractor;

public interface IContractorDao extends JpaRepository<Contractor, Long>{
	
	@Query("select contractor from Contractor contractor where active=true")
	List<Contractor> getAllActiveContractors();
	
}
