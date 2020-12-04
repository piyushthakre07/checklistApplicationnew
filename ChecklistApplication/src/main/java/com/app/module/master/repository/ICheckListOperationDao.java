package com.app.module.master.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.app.entities.CheckListOperation;

public interface ICheckListOperationDao extends JpaRepository<CheckListOperation, Long> {

	@Query("select checkListOperation from CheckListOperation checkListOperation where checkListOperation.checkListOperationId=?1 ")
	List<CheckListOperation> getCheckListOperationByCheckListOperationId(Long checkListOperationId);

	@Query("select checkListOperation from CheckListOperation checkListOperation WHERE (:projectId is null or checkListOperation.project.projectId = :projectId) and (:buildingId is null"
			+ " or checkListOperation.building.buildingId = :buildingId) and  (:flatId is null or checkListOperation.flat.flatId = :flatId) "
			+ " and  (:workTypeId is null or checkListOperation.workType.workTypeId = :workTypeId) ")
	List<CheckListOperation> getCheckListOperationReport(@Param("projectId") Long projectId,
			@Param("buildingId") Long buildingId, @Param("flatId") Long flatId, @Param("workTypeId") Long workTypeId);

}
