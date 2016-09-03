package com.at.library.service.book;

import java.util.List;

import org.springframework.data.domain.Pageable;

import com.at.library.dto.BookDTO;
import com.at.library.dto.BookPostDTO;
import com.at.library.enums.BookEnum;
import com.at.library.exceptions.BookNotFoundException;
import com.at.library.exceptions.BookWrongUpdateException;
import com.at.library.model.Book;

public interface BookService {

	/**
	 * Realiza la búsqueda de todos los libros existentes.
	 * 
	 * @param pageable
	 * @return Listado de todos los libros
	 */
	List<BookDTO> findAll(Pageable pageable);

	/**
	 * Realiza la búsqueda de un libro por id.
	 * 
	 * @param id
	 * @return Libro correspondiente al id buscado.
	 * @throws BookNotFoundException
	 */
	Book findOne(Integer id) throws BookNotFoundException;

	/**
	 * Realiza una búsqueda de libros según los parámetros incluidos.
	 * 
	 * @param isbn
	 * @param title
	 * @param author
	 * @param pageable
	 * @return Listado de libros que concuerden con los parámetros de búsqueda.
	 */
	List<BookDTO> search(String isbn, String title, String author, Pageable pageable);

	/**
	 * Crea un nuevo libro.
	 * 
	 * @param bookDTO
	 * @return El libro creado.
	 */
	BookDTO create(BookPostDTO bookDto);

	/**
	 * Actualiza un libro.
	 * 
	 * @param id
	 * @param book
	 * @throws BookWrongUpdateException
	 */
	void update(Integer id, BookDTO bookDto) throws BookWrongUpdateException;

	/**
	 * Elimina el libro correspondiente al id dado.
	 * 
	 * @param id
	 * @throws BookNotFoundException
	 */
	void delete(Integer id) throws BookNotFoundException;

	/**
	 * Comprueba la disponibilidad de un libro.
	 * 
	 * @param book
	 * @return Si el libro está disponible o no.
	 */
	Boolean isAvailable(Book book);

	/**
	 * Cambia el estado de un libro y lo guarda.
	 * 
	 * @param book
	 * @param newStatus
	 */
	void changeStatus(Book book, BookEnum newStatus);

	/**
	 * Realiza una migración de libros a partir de un servicio externo.
	 */
	void migration();

	/**
	 * Completa información sobre el libro consultando api externa.
	 * 
	 * @param book
	 */
	void setVolumeInfo(Book book);

}
