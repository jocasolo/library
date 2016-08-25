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

import com.at.library.dto.RoomDTO;
import com.at.library.service.room.RoomService;

@RestController
@RequestMapping(value = "/room")
public class RoomController {
	
	@Autowired
	private RoomService roomService;
	
	private static final Logger log = LoggerFactory.getLogger(RoomController.class);

	@RequestMapping(method = { RequestMethod.GET })
	public List<RoomDTO> getAll() {
		log.debug("Buscando todas las salas en el sistema.");
		return roomService.findAll();
	}

	@RequestMapping(method = { RequestMethod.POST })
	public RoomDTO create(@RequestBody RoomDTO room) {
		log.debug(String.format("Creando la sala: %s", room));
		return roomService.create(room);
	}

	@RequestMapping(value = "/{id}", method = { RequestMethod.GET })
	public RoomDTO findOne(@PathVariable("id") Integer id) {
		log.debug(String.format("Buscando la sala con id: %s", id));
		return roomService.findOne(id);
	}

	@RequestMapping(value = "/{id}", method = { RequestMethod.PUT })
	public void update(@PathVariable("id") Integer id, @RequestBody RoomDTO roomDTO) {
		log.debug(String.format("Modificando la sala: %s", roomDTO));
		roomService.update(roomDTO);
	}

	@RequestMapping(value = "/{id}", method = { RequestMethod.DELETE })
	public void delete(@PathVariable("id") Integer id) {
		log.debug(String.format("Borrando la sala con el id: %s", id));
		roomService.delete(id);
	}
}
