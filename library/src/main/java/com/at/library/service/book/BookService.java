package com.at.library.service.book;

import java.util.List;

import com.at.library.dto.BookDTO;
import com.at.library.model.Book;

public interface BookService {

	/**
	 * Realiza la búsqueda de todos los libros existentes.
	 * @return Listado de todos los libros.
	 */
	List<BookDTO> findAll();
	
	/**
	 * Realiza la búsqueda de un libro por id.
	 * 
	 * @param id
	 * @return Libro correspondiente al id buscado.
	 */
	BookDTO findOne(Integer id);

	/**
	 * Crea un nuevo libro.
	 * 
	 * @param bookDTO
	 * @return El libro creado.
	 */
	BookDTO create(BookDTO bookDTO);

	/**
	 * Actualiza un libro.
	 * 
	 * @param id
	 * @param book
	 */
	void update(BookDTO bookDTO);

	/**
	 * Elimina el libro correspondiente al id dado.
	 * 
	 * @param id
	 */
	void delete(Integer id);
	
	/**
	 * Transfrma un libro en un libroDTO
	 * 
	 * @param book
	 * @return
	 */
	BookDTO transform(Book book);

	/**
	 * Transforma un libroDTO en un libro
	 * 
	 * @param book
	 * @return
	 */
	Book transform(BookDTO book);

}
