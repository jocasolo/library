package com.at.library.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.at.library.dto.BookDTO;
import com.at.library.service.book.BookService;

@RestController
@RequestMapping(value = "/book")
public class BookController {

	@Autowired
	private BookService bookservice;

	private static final Logger log = LoggerFactory.getLogger(BookController.class);

	@RequestMapping(method = { RequestMethod.GET })
	public List<BookDTO> getAll() {
		log.debug("Buscando todos los libros en el sistema.");
		return bookservice.findAll();
	}
	
	@RequestMapping(value = "/{id}", method = { RequestMethod.GET })
	public BookDTO findOne(@PathVariable("id") Integer id) {
		log.debug(String.format("Buscando el libro con id: %s", id));
		return bookservice.transform(bookservice.findOne(id));
	}

	@RequestMapping(method = { RequestMethod.POST })
	public BookDTO create(@RequestBody BookDTO book) {
		log.debug(String.format("Creando el libro: %s", book));
		return bookservice.create(book);
	}

	@RequestMapping(value = "/{id}", method = { RequestMethod.PUT })
	public void update(@PathVariable("id") Integer id, @RequestBody BookDTO bookDTO) {
		log.debug(String.format("Modificando el libro: %s", bookDTO));
		bookservice.update(bookDTO);
	}

	@RequestMapping(value = "/{id}", method = { RequestMethod.DELETE })
	public void delete(@PathVariable("id") Integer id) {
		log.debug(String.format("Borrando el libro con el id: %s", id));
		bookservice.delete(id);
	}

}
