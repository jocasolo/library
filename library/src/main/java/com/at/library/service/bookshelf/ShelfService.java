package com.at.library.service.bookshelf;

import java.util.List;

import com.at.library.dto.BookshelfDTO;
import com.at.library.dto.BookshelfPutDTO;
import com.at.library.model.Bookshelf;

public interface ShelfService {

	/**
	 * Realiza la busqueda de todas las estanter�as existentes
	 * 
	 * @return listado de estanter�as
	 */
	List<BookshelfDTO> findAll();

	/**
	 * Realiza la b�squeda de una estanter�a por id.
	 * 
	 * @param id
	 * @return Sala correspondiente al id buscado.
	 */
	BookshelfDTO findOne(Integer id);

	/**
	 * Crea una nueva estanter�a.
	 * 
	 * @param bookshelfDTO
	 * @return La estanter�a creada.
	 */
	BookshelfDTO create(BookshelfDTO bookshelfDto);

	/**
	 * Actualiza una estanter�a.
	 * 
	 * @param bookshelf
	 */
	void update(BookshelfPutDTO bookshelfDto);

	/**
	 * Elimina la estanter�a correspondiente al id dado.
	 * 
	 * @param id
	 */
	void delete(Integer id);

	/**
	 * Transforma un Shelf en un ShelfDTO
	 * 
	 * @param bookshelfDto
	 * @return
	 */
	BookshelfDTO transform(Bookshelf bookshelf);

	/**
	 * Transforma un DTO en un objeto Shelf. 
	 * @param bookshelfDto
	 * @return
	 */
	<T> Bookshelf transform(T bookshelfDto);

}
