package com.at.library.service.bookshelf;

import java.util.List;

import org.springframework.data.domain.Pageable;

import com.at.library.dto.BookshelfDTO;
import com.at.library.dto.BookshelfPostDTO;
import com.at.library.exceptions.BookshelfAlreadyExistsException;
import com.at.library.exceptions.BookshelfNotFoundException;

public interface BookshelfService {

	/**
	 * Realiza la busqueda de todas las estanterías existentes
	 * 
	 * @return Listado de estanterías
	 */
	List<BookshelfDTO> findAll(Pageable pageable);

	/**
	 * Realiza la búsqueda de una estantería por id.
	 * 
	 * @param id
	 * @return Estantería correspondiente al id buscado.
	 * @throws BookshelfNotFoundException
	 */
	BookshelfDTO findOne(String code) throws BookshelfNotFoundException;

	/**
	 * Crea una nueva estantería.
	 * 
	 * @param bookshelfDTO
	 * @return La estantería creada.
	 * @throws BookshelfAlreadyExistsException 
	 */
	BookshelfDTO create(BookshelfPostDTO bookshelfDto) throws BookshelfAlreadyExistsException;

}
