package com.app.module.master.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.app.entities.CheckListOperationTaskDetails;

public interface ICheckListOperationTaskDetailsDao extends JpaRepository<CheckListOperationTaskDetails, Long> {
}
