package com.at.library.service.room;

import java.util.List;

import com.at.library.dto.RoomDTO;
import com.at.library.model.Room;

public interface RoomService {

	/**
	 * Realiza la busqueda de todas las salas existentes
	 * 
	 * @return listado de salas
	 */
	List<RoomDTO> findAll();

	/**
	 * Transforma un Room en un RoomDTO
	 * 
	 * @param room
	 * @return
	 */
	RoomDTO transform(Room room);

	/**
	 * Transforma un RoomDTO en un Room
	 * 
	 * @param room
	 * @return
	 */
	Room transform(RoomDTO room);

}
