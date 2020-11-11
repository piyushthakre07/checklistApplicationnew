package com.app.module.master.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.app.entities.WorkType;

public interface IWorkTypeDao extends JpaRepository<WorkType, Long> {

	@Query("select workType from WorkType workType where active=true")
	List<WorkType> getAllActiveWorkTypes();
	
	@Query("select workType from WorkType workType where workType.workTypeId=?1 ")
	List<WorkType> getWorkTypeByWorkTypeId(Long workTypeId);
}