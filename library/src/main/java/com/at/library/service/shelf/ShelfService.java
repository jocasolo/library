package com.at.library.service.shelf;

import java.util.List;

import com.at.library.dto.ShelfDTO;
import com.at.library.model.Shelf;

public interface ShelfService {

	/**
	 * Realiza la busqueda de todos las estanterias existentes
	 * 
	 * @return listado de estanterias
	 */
	List<ShelfDTO> findAll();

	/**
	 * Transforma un Shelf en un ShelfDTO
	 * 
	 * @param shelf
	 * @return
	 */
	ShelfDTO transform(Shelf shelf);

	/**
	 * Transforma un ShelfDTO en un Shelf
	 * 
	 * @param shelf
	 * @return
	 */
	Shelf transform(ShelfDTO shelf);

}
