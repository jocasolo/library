package com.at.library.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.at.library.dto.RentDTO;
import com.at.library.dto.RentPostDTO;
import com.at.library.service.rent.RentService;

@RestController
@RequestMapping(value = "/rent")
public class RentController {
	
	@Autowired
	private RentService rentService;
	
	private static final Logger log = LoggerFactory.getLogger(BookController.class);
	
	@RequestMapping(method = { RequestMethod.GET })
	public List<RentDTO> getAll(){
		return rentService.findAll();
	}
	
	@RequestMapping(method = { RequestMethod.POST })
	public RentDTO create(@RequestBody RentPostDTO rent) {
		log.debug(String.format("Creando el alquiler: %s", rent));
		return rentService.create(rent);
	}
	
	@RequestMapping(value = "/restore/{idBook}", method = { RequestMethod.POST })
	public RentDTO restore(Integer idBook){
		log.debug(String.format("Devolviendo alquiler con el libro con id: %s", idBook));
		return rentService.restore(idBook);
	}
	
}
