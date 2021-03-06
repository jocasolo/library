package com.at.library.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.at.library.dto.BookDTO;
import com.at.library.dto.BookPostDTO;
import com.at.library.exceptions.BookInvalidStatusException;
import com.at.library.exceptions.BookNotFoundException;
import com.at.library.exceptions.BookWrongUpdateException;
import com.at.library.service.CommonService;
import com.at.library.service.book.BookService;

import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping(value = "/book")
public class BookController {

	@Autowired
	private BookService bookservice;
	
	@Autowired
	private CommonService commonService;

	private static final Logger log = LoggerFactory.getLogger(BookController.class);

	@ApiOperation(value = "Buscar un libro seg�n su id.")
	@RequestMapping(value = "/{id}", method = { RequestMethod.GET })
	public BookDTO findOne(@PathVariable("id") Integer id) throws BookNotFoundException {
		log.debug(String.format("Buscando el libro con id: %s", id));
		return commonService.transform(bookservice.findOne(id), BookDTO.class);
	}

	@ApiOperation(value = "Buscar todos los libros que coincidan con los par�metros de b�squeda.")
	@RequestMapping(method = { RequestMethod.GET })
	public List<BookDTO> search(
			@RequestParam(value = "isbn", required = false) String isbn,
			@RequestParam(value = "title", required = false) String title,
			@RequestParam(value = "author", required = false) String author,
			@RequestParam(value = "page", required = false, defaultValue = "0") Integer page,
			@RequestParam(value = "size", required = false, defaultValue = "10") Integer size) {
		log.debug(String.format("Buscando seg�n los campos: %s, %s, %s", isbn, title, author));
		return bookservice.search(isbn, title, author, new PageRequest(page, size));
	}

	@ApiOperation(value = "Crear un nuevo libro.")
	@RequestMapping(method = { RequestMethod.POST })
	public BookDTO create(@RequestBody BookPostDTO book) {
		log.debug(String.format("Creando el libro: %s", book));
		return bookservice.create(book);
	}

	@ApiOperation(value = "Actualizar un libro seg�n su id.")
	@RequestMapping(value = "/{id}", method = { RequestMethod.PUT })
	public void update(
			@PathVariable("id") Integer id, 
			@RequestBody BookDTO bookDTO) throws BookWrongUpdateException, BookInvalidStatusException {
		log.debug(String.format("Modificando el libro: %s", bookDTO));
		bookservice.update(id, bookDTO);
	}

	@ApiOperation(value = "Borrar un libro seg�n su id.")
	@RequestMapping(value = "/{id}", method = { RequestMethod.DELETE })
	public void delete(@PathVariable("id") Integer id) throws BookNotFoundException {
		log.debug(String.format("Borrando el libro con el id: %s", id));
		bookservice.delete(id);
	}

}
