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

import com.at.library.dto.HistoryRentedDTO;
import com.at.library.dto.RentDTO;
import com.at.library.dto.RentPostDTO;
import com.at.library.exceptions.BookNotFoundException;
import com.at.library.exceptions.BookRentedException;
import com.at.library.exceptions.UserBannedException;
import com.at.library.exceptions.UserNotFoundException;
import com.at.library.service.rent.RentService;

@RestController
public class RentController {

	@Autowired
	private RentService rentService;

	private static final Logger log = LoggerFactory.getLogger(BookController.class);

	@RequestMapping(value = "/book/{id}/rent", method = { RequestMethod.GET })
	public List<HistoryRentedDTO> getBookHistory(@PathVariable("id") Integer idBook) throws BookNotFoundException {
		log.debug(String.format("Historial del libro con id: %s", idBook));
		return rentService.getBookHistory(idBook);
	}

	@RequestMapping(value = "book/{id}/rent", method = { RequestMethod.DELETE })
	public RentDTO restore(@PathVariable("id") Integer idBook) throws BookNotFoundException {
		log.debug(String.format("Devolviendo alquiler con el libro con id: %s", idBook));
		return rentService.restore(idBook);
	}
	
	@RequestMapping(value = "/book/{id}/rent", method = { RequestMethod.POST })
	public RentDTO create(@PathVariable("id") Integer idBook, @RequestBody RentPostDTO rent) throws BookRentedException, BookNotFoundException, UserBannedException {
		log.debug(String.format("Creando el alquiler: %s", rent));
		return rentService.create(idBook, rent);
	}
	
	@RequestMapping(value = "/user/{id}/rent", method = { RequestMethod.GET })
	public List<HistoryRentedDTO> getUserHistoy(@PathVariable("id") Integer idUser) throws UserNotFoundException {
		log.debug(String.format("Historial del usuario con id: %s", idUser));
		return rentService.getUserHistory(idUser);
	}
	
	@RequestMapping(value = "/rent", method = { RequestMethod.GET })
	public List<RentDTO> getAll() {
		log.debug(String.format("Obteniedno todos los alquileres"));
		return rentService.findAll();
	}
	

}
