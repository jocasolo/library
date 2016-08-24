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

import com.at.library.dto.ShelfDTO;
import com.at.library.dto.ShelfPutDTO;
import com.at.library.service.shelf.ShelfService;

@RestController
@RequestMapping(value = "/shelf")
public class ShelfController {

	@Autowired
	private ShelfService shelfService;
	
	private static final Logger log = LoggerFactory.getLogger(ShelfController.class);

	@RequestMapping(method = { RequestMethod.GET })
	public List<ShelfDTO> getAll() {
		log.debug("Buscando todas las estanterías en el sistema.");
		return shelfService.findAll();
	}

	@RequestMapping(method = { RequestMethod.POST })
	public ShelfDTO create(@RequestBody ShelfDTO shelf) {
		log.debug(String.format("Creando la estantería: %s", shelf));
		return shelfService.create(shelf);
	}

	@RequestMapping(value = "/{id}", method = { RequestMethod.GET })
	public ShelfDTO findOne(@PathVariable("id") Integer id) {
		log.debug(String.format("Buscando la estantería con id: %s", id));
		return shelfService.findOne(id);
	}

	@RequestMapping(value = "/{id}", method = { RequestMethod.PUT })
	public void update(@PathVariable("id") Integer id, @RequestBody ShelfPutDTO shelfDTO) {
		log.debug(String.format("Modificando la estantería: %s", shelfDTO));
		shelfService.update(shelfDTO);
	}

	@RequestMapping(value = "/{id}", method = { RequestMethod.DELETE })
	public void delete(@PathVariable("id") Integer id) {
		log.debug(String.format("Borrando la estantería con el id: %s", id));
		shelfService.delete(id);
	}
}
