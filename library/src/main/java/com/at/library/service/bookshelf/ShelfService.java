package com.at.library.service.bookshelf;

import java.util.List;

import com.at.library.dto.BookshelfDTO;
import com.at.library.dto.BookshelfPutDTO;
import com.at.library.model.Bookshelf;

public interface ShelfService {

	/**
	 * Realiza la busqueda de todas las estanterías existentes
	 * 
	 * @return listado de estanterías
	 */
	List<BookshelfDTO> findAll();

	/**
	 * Realiza la búsqueda de una estantería por id.
	 * 
	 * @param id
	 * @return Sala correspondiente al id buscado.
	 */
	BookshelfDTO findOne(Integer id);

	/**
	 * Crea una nueva estantería.
	 * 
	 * @param bookshelfDTO
	 * @return La estantería creada.
	 */
	BookshelfDTO create(BookshelfDTO bookshelfDto);

	/**
	 * Actualiza una estantería.
	 * 
	 * @param bookshelf
	 */
	void update(BookshelfPutDTO bookshelfDto);

	/**
	 * Elimina la estantería correspondiente al id dado.
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
