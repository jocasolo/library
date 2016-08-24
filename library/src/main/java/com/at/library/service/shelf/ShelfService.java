package com.at.library.service.shelf;

import java.util.List;

import com.at.library.dto.ShelfDTO;
import com.at.library.dto.ShelfPutDTO;
import com.at.library.model.Shelf;

public interface ShelfService {

	/**
	 * Realiza la busqueda de todas las estanter�as existentes
	 * 
	 * @return listado de estanter�as
	 */
	List<ShelfDTO> findAll();

	/**
	 * Realiza la b�squeda de una estanter�a por id.
	 * 
	 * @param id
	 * @return Sala correspondiente al id buscado.
	 */
	ShelfDTO findOne(Integer id);

	/**
	 * Crea una nueva estanter�a.
	 * 
	 * @param shelfDTO
	 * @return La estanter�a creada.
	 */
	ShelfDTO create(ShelfDTO shelfDto);

	/**
	 * Actualiza una estanter�a.
	 * 
	 * @param shelf
	 */
	void update(ShelfPutDTO shelfDto);

	/**
	 * Elimina la estanter�a correspondiente al id dado.
	 * 
	 * @param id
	 */
	void delete(Integer id);

	/**
	 * Transforma un Shelf en un ShelfDTO
	 * 
	 * @param shelf
	 * @return
	 */
	ShelfDTO transform(Shelf shelf);

	/**
	 * Transforma un DTO en un objeto Shelf. 
	 * @param shelf
	 * @return
	 */
	<T> Shelf transform(T shelfDto);

}
