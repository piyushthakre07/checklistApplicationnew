package com.app.module.master.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.app.entities.CheckListOperation;

public interface ICheckListOperationDao extends JpaRepository<CheckListOperation, Long> {

	@Query("select checkListOperation from CheckListOperation checkListOperation where checkListOperation.checkListOperationId=?1 ")
	List<CheckListOperation> getCheckListOperationByCheckListOperationId(Long checkListOperationId);
}
