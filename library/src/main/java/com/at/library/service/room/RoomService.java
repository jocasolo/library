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
	 * Realiza la b�squeda de una sala por id.
	 * 
	 * @param id
	 * @return Sala correspondiente al id buscado.
	 */
	RoomDTO findOne(Integer id);

	/**
	 * Crea una nueva sala.
	 * 
	 * @param roomDTO
	 * @return La sala creada.
	 */
	RoomDTO create(RoomDTO roomDTO);

	/**
	 * Actualiza una sala.
	 * 
	 * @param id
	 * @param room
	 */
	void update(Integer id, RoomDTO roomDTO);

	/**
	 * Elimina la sala correspondiente al id dado.
	 * 
	 * @param id
	 */
	void delete(Integer id);

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
