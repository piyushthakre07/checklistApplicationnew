package com.app.module.master.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.app.entities.Project;

/**
 * @author Piyush.Thakre
 *
 */
public interface IProjectDao extends JpaRepository<Project, Long> {

	@Query("select project from Project project where active=true")
	List<Project> getAllActiveProjects();

	@Query("select project from Project project where project.projectId=?1 ")
	List<Project> getProjectByProjectId(Long projectId);
}
