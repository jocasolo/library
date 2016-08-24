package com.at.library.service.room;

import java.util.List;

import com.at.library.dto.RoomDTO;
import com.at.library.dto.RoomPutDTO;
import com.at.library.model.Room;

public interface RoomService {

	/**
	 * Realiza la busqueda de todas las salas existentes
	 * 
	 * @return listado de salas
	 */
	List<RoomDTO> findAll();

	/**
	 * Realiza la búsqueda de una sala por id.
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
	 * @param room
	 */
	void update(RoomPutDTO roomDTO);

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
	 * Transforma un DTO en un objeto Room. 
	 * @param room
	 * @return
	 */
	<T> Room transform(T room);

}
