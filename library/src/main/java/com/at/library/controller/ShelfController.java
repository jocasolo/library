package com.at.library.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.at.library.dto.ShelfDTO;
import com.at.library.service.shelf.ShelfService;

@RestController
public class ShelfController {

	@Autowired
	private ShelfService shelfService;
	
	@RequestMapping(value = "/shelf", method = { RequestMethod.GET })
	public List<ShelfDTO> getAll(){
		return shelfService.findAll();
	}
}
