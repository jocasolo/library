package com.at.library.service.room;

import java.util.List;

import org.springframework.data.domain.Pageable;

import com.at.library.dto.RoomDTO;
import com.at.library.dto.RoomPostDTO;
import com.at.library.exceptions.RoomAlreadyExistsException;
import com.at.library.exceptions.RoomNotFoundException;

public interface RoomService {

	/**
	 * Realiza la busqueda de todas las salas existentes
	 * 
	 * @return Listado de salas
	 */
	List<RoomDTO> findAll(Pageable pageable);

	/**
	 * Realiza la búsqueda de una sala por id.
	 * 
	 * @param code
	 * @return Sala correspondiente al id buscado.
	 * @throws RoomNotFoundException 
	 */
	RoomDTO findOne(String code) throws RoomNotFoundException;

	/**
	 * Crea una nueva sala.
	 * 
	 * @param roomDTO
	 * @return La sala creada.
	 * @throws RoomAlreadyExistsException 
	 */
	RoomDTO create(RoomPostDTO roomDto) throws RoomAlreadyExistsException;

}
