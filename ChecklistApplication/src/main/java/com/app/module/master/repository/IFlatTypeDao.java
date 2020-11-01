package com.app.module.master.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.app.entities.FlatType;

public interface IFlatTypeDao extends JpaRepository<FlatType, Long> {

	@Query("select flatType from FlatType flatType where active=true")
	List<FlatType> getAllActiveFlatTypes();
}
