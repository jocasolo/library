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

import com.at.library.dto.HistoryRentedDTO;
import com.at.library.dto.RentDTO;
import com.at.library.dto.RentPostDTO;
import com.at.library.exceptions.BookNotFoundException;
import com.at.library.exceptions.BookNotRentedException;
import com.at.library.exceptions.BookRentedException;
import com.at.library.exceptions.EmployeeNotFoundException;
import com.at.library.exceptions.UserBannedException;
import com.at.library.exceptions.UserNotFoundException;
import com.at.library.service.rent.RentService;

import io.swagger.annotations.ApiOperation;

@RestController
public class RentController {

	@Autowired
	private RentService rentService;

	private static final Logger log = LoggerFactory.getLogger(BookController.class);

	@ApiOperation(value = "Obtener historial de alquileres de un determinado libro.")
	@RequestMapping(value = "/book/{id}/rent", method = { RequestMethod.GET })
	public List<HistoryRentedDTO> getBookHistory(
			@PathVariable("id") Integer idBook,
			@RequestParam(value = "page", required = false, defaultValue = "0") Integer page,
			@RequestParam(value = "size", required = false, defaultValue = "10") Integer size) throws BookNotFoundException {
		log.debug(String.format("Historial del libro con id: %s", idBook));
		return rentService.getBookHistory(idBook, new PageRequest(page, size));
	}

	@ApiOperation(value = "Realizar la devolución de un libro.")
	@RequestMapping(value = "/rent/{idBook}", method = { RequestMethod.DELETE }) // "/book/{id}/rent"
	public RentDTO restore(@PathVariable("idBook") Integer idBook) throws BookNotFoundException, BookNotRentedException {
		log.debug(String.format("Devolviendo alquiler con el libro con id: %s", idBook));
		return rentService.restore(idBook);
	}
	
	@ApiOperation(value = "Crear un nuevo alquiler.")
	@RequestMapping(value = "/rent" , method = { RequestMethod.POST })  // "/book/{id}/rent"
	public RentDTO create(
			@RequestBody RentPostDTO rent) throws BookRentedException, BookNotFoundException, UserBannedException, UserNotFoundException, EmployeeNotFoundException {
		log.debug(String.format("Creando el alquiler: %s", rent));
		return rentService.create(rent);
	}
	
	@ApiOperation(value = "Obtener historial de alquileres de un determinado usuario.")
	@RequestMapping(value = "/user/{id}/rent", method = { RequestMethod.GET })
	public List<HistoryRentedDTO> getUserHistoy(
			@PathVariable("id") Integer idUser,
			@RequestParam(value = "page", required = false, defaultValue = "0") Integer page,
			@RequestParam(value = "size", required = false, defaultValue = "10") Integer size) throws UserNotFoundException {
		log.debug(String.format("Historial del usuario con id: %s", idUser));
		return rentService.getUserHistory(idUser, new PageRequest(page, size));
	}
	
	@ApiOperation(value = "Obtener todos los alquileres.")
	@RequestMapping(value = "/rent", method = { RequestMethod.GET })
	public List<RentDTO> getAll(
			@RequestParam(value = "page", required = false, defaultValue = "0") Integer page,
			@RequestParam(value = "size", required = false, defaultValue = "10") Integer size) {
		log.debug(String.format("Obteniedno todos los alquileres"));
		return rentService.findAll(new PageRequest(page, size));
	}
	

}
