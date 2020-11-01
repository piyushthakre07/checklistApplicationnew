package com.app.module.master.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.app.entities.Room;

public interface IRoomDao extends JpaRepository<Room, Long> {

	@Query("select room from Room room where active=true")
	List<Room> getAllActiveRooms();
}
