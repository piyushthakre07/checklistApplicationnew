package com.app.module.master.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.app.entities.CheckListOperation;
import com.app.entities.CheckListOperationTaskDetails;

public interface ICheckListOperationTaskDetailsDao extends JpaRepository<CheckListOperationTaskDetails, Long> {

	@Query("select checkListOperationTaskDetails.checkListOperation from CheckListOperationTaskDetails checkListOperationTaskDetails where checkListOperationTaskDetails.task.taskId= :taskId and checkListOperationTaskDetails.checkListOperation.flat.flatId= :flatId and  checkListOperationTaskDetails.checkListOperation.workType.workTypeId=:workTypeId")
	List<CheckListOperation> getCheckListOperationTaskDetailsByFlatIdAndAndWorTypeAndTaskId(@Param("flatId") Long flatId,
			 @Param("workTypeId") Long workTypeId,  @Param("taskId") Long taskId);
}
