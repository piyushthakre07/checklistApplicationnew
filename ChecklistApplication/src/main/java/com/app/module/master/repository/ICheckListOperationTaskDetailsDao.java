package com.app.module.master.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.app.entities.CheckListOperation;
import com.app.entities.CheckListOperationTaskDetails;

public interface ICheckListOperationTaskDetailsDao extends JpaRepository<CheckListOperationTaskDetails, Long> {

	@Query("select checkListOperationTaskDetails.checkListOperation from CheckListOperationTaskDetails checkListOperationTaskDetails where checkListOperationTaskDetails.task.taskId= :taskId and checkListOperationTaskDetails.checkListOperation.flat.flatId= :flatId and  checkListOperationTaskDetails.checkListOperation.workType.workTypeId=:workTypeId")
	List<CheckListOperation> getCheckListOperationTaskDetailsByFlatIdAndAndWorTypeAndTaskId(
			@Param("flatId") Long flatId, @Param("workTypeId") Long workTypeId, @Param("taskId") Long taskId);

	@Query("select checkListOperationTaskDetails.checkListOperation from CheckListOperationTaskDetails checkListOperationTaskDetails where checkListOperationTaskDetails.task.taskId= :taskId and checkListOperationTaskDetails.checkListOperation.flat.flatId= :flatId and  checkListOperationTaskDetails.checkListOperation.workType.workTypeId=:workTypeId and checkListOperationTaskDetails.room.roomId= :roomId ")
	List<CheckListOperation> getCheckListOperationTaskDetailsByFlatIdAndAndWorTypeAndTaskId(
			@Param("flatId") Long flatId, @Param("workTypeId") Long workTypeId, @Param("taskId") Long taskId,
			@Param("roomId") Long roomId);

	@Query("select checkListOperationTaskDetails from CheckListOperationTaskDetails checkListOperationTaskDetails where checkListOperationTaskDetails.task.taskId= :taskId and checkListOperationTaskDetails.room.roomId= :roomId and checkListOperationTaskDetails.checkListOperation.checkListOperationId= :checkListOperationId and checkListOperationTaskDetails.is_owner= :owner and checkListOperationTaskDetails.is_user= :user")
	List<CheckListOperationTaskDetails> getCheckListOperationTaskDetailsByTaskIdNRoomIdNCheckListOperationIdNIsOwner(
			@Param("taskId") Long taskId, @Param("roomId") Long roomId,
			@Param("checkListOperationId") Long checkListOperationId, @Param("owner") boolean owner,
			@Param("user") boolean user);
	
	@Query("select checkListOperationTaskDetails from CheckListOperationTaskDetails checkListOperationTaskDetails where checkListOperationTaskDetails.task.taskId= :taskId and checkListOperationTaskDetails.checkListOperation.flat.flatId= :flatId and  checkListOperationTaskDetails.checkListOperation.workType.workTypeId=:workTypeId and checkListOperationTaskDetails.room.roomId= :roomId ")
	List<CheckListOperationTaskDetails> getCheckListOperationTaskDetailsByFlatIdAndAndWorTypeAndTaskIdAndRoomId(
			@Param("flatId") Long flatId, @Param("workTypeId") Long workTypeId, @Param("taskId") Long taskId,
			@Param("roomId") Long roomId);

}
