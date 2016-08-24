package com.at.library.service.shelf;

import java.util.List;

import com.at.library.dto.ShelfDTO;
import com.at.library.dto.ShelfPutDTO;
import com.at.library.model.Shelf;

public interface ShelfService {

	/**
	 * Realiza la busqueda de todas las estanterías existentes
	 * 
	 * @return listado de estanterías
	 */
	List<ShelfDTO> findAll();

	/**
	 * Realiza la búsqueda de una estantería por id.
	 * 
	 * @param id
	 * @return Sala correspondiente al id buscado.
	 */
	ShelfDTO findOne(Integer id);

	/**
	 * Crea una nueva estantería.
	 * 
	 * @param shelfDTO
	 * @return La estantería creada.
	 */
	ShelfDTO create(ShelfDTO shelfDto);

	/**
	 * Actualiza una estantería.
	 * 
	 * @param shelf
	 */
	void update(ShelfPutDTO shelfDto);

	/**
	 * Elimina la estantería correspondiente al id dado.
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
