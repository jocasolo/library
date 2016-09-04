package com.at.library.dao;

import java.util.List;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.at.library.model.Room;

@Repository
public interface RoomDAO extends CrudRepository<Room, Integer> {

	@Query(value = "SELECT r FROM Room AS r")
	public List<Room> findAll(Pageable pageable);

	@Query(value = "SELECT r FROM Room AS r WHERE r.code = :code")
	public Room findOne(@Param("code") String code);

}
