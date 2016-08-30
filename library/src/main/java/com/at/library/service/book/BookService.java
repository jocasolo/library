package com.at.library.service.book;

import java.util.List;

import com.at.library.dto.BookDTO;
import com.at.library.enums.StatusEnum;
import com.at.library.exceptions.BookNotFoundException;
import com.at.library.exceptions.BookWrongUpdateException;
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
	 * @throws BookNotFoundException 
	 */
	Book findOne(Integer id) throws BookNotFoundException;
	
	/**
	 * Realiza una búsqueda de libros según los parámetros incluidos.
	 * 
	 * @return
	 */
	List<BookDTO> search(String isbn, String title, String author);

	/**
	 * Crea un nuevo libro.
	 * 
	 * @param bookDTO
	 * @return El libro creado.
	 */
	BookDTO create(BookDTO bookDto);

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
	 * Transfrma un libro en un libroDTO.
	 * 
	 * @param book
	 * @return
	 */
	BookDTO transform(Book book);

	/**
	 * Transforma un DTO de libro en un libro.
	 * 
	 * @param book
	 * @return
	 */
	<T> Book transform(T book);
	
	/**
	 * Transforma una lista de libros en una lista de libros DTO.
	 * 
	 * @param books
	 * @return
	 */
	List<BookDTO> transform(List<Book> books);
	
	/**
	 * Comprueba la disponibilidad de un libro.
	 * 
	 * @param book
	 * @return 
	 */
	Boolean isAvailable(Book book);
	
	/**
	 * Comprueba la disponibilidad de un libro dado su id.
	 * 
	 * @param id
	 * @return
	 * @throws BookNotFoundException 
	 */
	Boolean isAvailable(Integer id) throws BookNotFoundException;
	
	/**
	 * Cambia el estado de un libro.
	 * 
	 * @param book
	 * @param newStatus
	 */
	void changeStatus(Book book, StatusEnum newStatus);
	
	void migration();

}
