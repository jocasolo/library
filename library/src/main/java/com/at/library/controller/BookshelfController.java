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

import com.at.library.dto.BookshelfDTO;
import com.at.library.service.bookshelf.ShelfService;

@RestController
@RequestMapping(value = "/bookshelf")
public class BookshelfController {

	@Autowired
	private ShelfService shelfService;
	
	private static final Logger log = LoggerFactory.getLogger(BookshelfController.class);

	@RequestMapping(method = { RequestMethod.GET })
	public List<BookshelfDTO> getAll() {
		log.debug("Buscando todas las estanter�as en el sistema.");
		return shelfService.findAll();
	}

	@RequestMapping(method = { RequestMethod.POST })
	public BookshelfDTO create(@RequestBody BookshelfDTO shelf) {
		log.debug(String.format("Creando la estanter�a: %s", shelf));
		return shelfService.create(shelf);
	}

	@RequestMapping(value = "/{id}", method = { RequestMethod.GET })
	public BookshelfDTO findOne(@PathVariable("id") Integer id) {
		log.debug(String.format("Buscando la estanter�a con id: %s", id));
		return shelfService.findOne(id);
	}

	@RequestMapping(value = "/{id}", method = { RequestMethod.PUT })
	public void update(@PathVariable("id") Integer id, @RequestBody BookshelfDTO shelfDTO) {
		log.debug(String.format("Modificando la estanter�a: %s", shelfDTO));
		shelfService.update(shelfDTO);
	}

	@RequestMapping(value = "/{id}", method = { RequestMethod.DELETE })
	public void delete(@PathVariable("id") Integer id) {
		log.debug(String.format("Borrando la estanter�a con el id: %s", id));
		shelfService.delete(id);
	}
}