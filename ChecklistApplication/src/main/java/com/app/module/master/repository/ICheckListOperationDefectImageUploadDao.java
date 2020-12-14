package com.app.module.master.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.app.entities.CheckListOperationDefectImageUpload;

public interface ICheckListOperationDefectImageUploadDao
		extends JpaRepository<CheckListOperationDefectImageUpload, Long> {

	@Query("select checkListOperationDefectImageUpload from CheckListOperationDefectImageUpload checkListOperationDefectImageUpload where checkListOperationDefectImageUpload.checkListOperationTaskDetails.task.taskId= :taskId and checkListOperationDefectImageUpload.checkListOperationTaskDetails.checkListOperation.flat.flatId= :flatId and  checkListOperationDefectImageUpload.checkListOperationTaskDetails.checkListOperation.workType.workTypeId=:workTypeId and checkListOperationDefectImageUpload.checkListOperationTaskDetails.room.roomId= :roomId ")
	List<CheckListOperationDefectImageUpload> getCheckListOperationDefectImageUploadByFlatIdAndAndWorTypeAndTaskId(
			@Param("flatId") Long flatId, @Param("workTypeId") Long workTypeId, @Param("taskId") Long taskId,
			@Param("roomId") Long roomId);

}
