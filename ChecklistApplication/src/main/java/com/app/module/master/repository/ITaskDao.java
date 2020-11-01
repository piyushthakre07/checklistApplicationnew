package com.app.module.master.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.app.entities.Task;

public interface ITaskDao extends JpaRepository<Task, Long> {

	@Query("select task from Task task where active=true")
	List<Task> getAllActiveTasks();
}
