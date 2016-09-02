package com.at.library.service.bookshelf;

import java.util.List;

import org.springframework.data.domain.Pageable;

import com.at.library.dto.BookshelfDTO;
import com.at.library.exceptions.BookshelfNotFoundException;
import com.at.library.model.Bookshelf;

public interface BookshelfService {

	/**
	 * Realiza la busqueda de todas las estanterías existentes
	 * 
	 * @return listado de estanterías
	 */
	List<BookshelfDTO> findAll(Pageable pageable);

	/**
	 * Realiza la búsqueda de una estantería por id.
	 * 
	 * @param id
	 * @return Sala correspondiente al id buscado.
	 * @throws BookshelfNotFoundException 
	 */
	BookshelfDTO findOne(Integer id) throws BookshelfNotFoundException;

	/**
	 * Crea una nueva estantería.
	 * 
	 * @param bookshelfDTO
	 * @return La estantería creada.
	 */
	BookshelfDTO create(BookshelfDTO bookshelfDto);

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
