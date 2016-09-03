package com.at.library.service.room;

import java.util.List;

import org.springframework.data.domain.Pageable;

import com.at.library.dto.RoomDTO;

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
	RoomDTO create(RoomDTO roomDto);

}
