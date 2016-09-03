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

import com.at.library.dto.BookshelfDTO;
import com.at.library.exceptions.BookshelfNotFoundException;
import com.at.library.service.bookshelf.BookshelfService;

import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping(value = "/bookshelf")
public class BookshelfController {

	@Autowired
	private BookshelfService shelfService;
	
	private static final Logger log = LoggerFactory.getLogger(BookshelfController.class);

	@ApiOperation(value = "Obtener todas las estanter�as.")
	@RequestMapping(method = { RequestMethod.GET })
	public List<BookshelfDTO> getAll(
			@RequestParam(value = "page", required = false, defaultValue = "0") Integer page,
			@RequestParam(value = "size", required = false, defaultValue = "10") Integer size) {
		log.debug("Buscando todas las estanter�as en el sistema.");
		return shelfService.findAll(new PageRequest(page, size));
	}

	@ApiOperation(value = "Crear una nueva estanter�a.")
	@RequestMapping(method = { RequestMethod.POST })
	public BookshelfDTO create(@RequestBody BookshelfDTO shelf) {
		log.debug(String.format("Creando la estanter�a: %s", shelf));
		return shelfService.create(shelf);
	}

	@ApiOperation(value = "Buscar una estanter�a seg�n su id.")
	@RequestMapping(value = "/{id}", method = { RequestMethod.GET })
	public BookshelfDTO findOne(@PathVariable("id") Integer id) throws BookshelfNotFoundException {
		log.debug(String.format("Buscando la estanter�a con id: %s", id));
		return shelfService.findOne(id);
	}

	@ApiOperation(value = "Actualizar una estanter�a seg�n su id.")
	@RequestMapping(value = "/{id}", method = { RequestMethod.PUT })
	public void update(
			@PathVariable("id") Integer id, 
			@RequestBody BookshelfDTO shelfDTO) {
		throw new UnsupportedOperationException("A�n no implementado.");
	}

	@ApiOperation(value = "Borrar una estanter�a seg�n su id.")
	@RequestMapping(value = "/{id}", method = { RequestMethod.DELETE })
	public void delete(@PathVariable("id") Integer id) {
		throw new UnsupportedOperationException("A�n no implementado.");
	}
}
