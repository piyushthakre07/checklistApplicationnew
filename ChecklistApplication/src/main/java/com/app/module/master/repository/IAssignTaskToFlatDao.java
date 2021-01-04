package com.app.module.master.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.app.entities.AssignTaskToFlat;
import com.app.entities.WorkType;

/**
 * @author Piyush.Thakre
 *
 */
public interface IAssignTaskToFlatDao extends JpaRepository<AssignTaskToFlat, Long> {

	@Query("select assignTaskToFlat from AssignTaskToFlat assignTaskToFlat where active=true")
	List<AssignTaskToFlat> getActiveAssignTaskToFlats();

	@Query("select assignTaskToFlat from AssignTaskToFlat assignTaskToFlat where assignTaskToFlat.flat.flatId=?1 and  assignTaskToFlat.workType.workTypeId=?2  ")
	List<AssignTaskToFlat> getTaskByFlatIdAndWorktype(Long flatId, Long workTypeId);
	
	@Query("select assignTaskToFlat from AssignTaskToFlat assignTaskToFlat where assignTaskToFlat.assignTaskToFlatId=?1 ")
	List<AssignTaskToFlat> getAssignTaskToFlatByAssignTaskToFlatId(Long assignTaskToFlatId);
	
	@Query("select distinct assignTaskToFlat.workType from AssignTaskToFlat assignTaskToFlat where assignTaskToFlat.flat.flatId=?1  ")
	List<WorkType> getWorkTypeByFlatId(Long flatId);

}
