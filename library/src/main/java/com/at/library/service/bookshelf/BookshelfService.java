package com.at.library.service.bookshelf;

import java.util.List;

import org.springframework.data.domain.Pageable;

import com.at.library.dto.BookshelfDTO;
import com.at.library.exceptions.BookshelfNotFoundException;

public interface BookshelfService {

	/**
	 * Realiza la busqueda de todas las estanter�as existentes
	 * 
	 * @return listado de estanter�as
	 */
	List<BookshelfDTO> findAll(Pageable pageable);

	/**
	 * Realiza la b�squeda de una estanter�a por id.
	 * 
	 * @param id
	 * @return Sala correspondiente al id buscado.
	 * @throws BookshelfNotFoundException
	 */
	BookshelfDTO findOne(Integer id) throws BookshelfNotFoundException;

	/**
	 * Crea una nueva estanter�a.
	 * 
	 * @param bookshelfDTO
	 * @return La estanter�a creada.
	 */
	BookshelfDTO create(BookshelfDTO bookshelfDto);

}
