package com.at.library.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.at.library.dto.RentDTO;
import com.at.library.service.rent.RentService;

@RestController
public class RentController {
	
	@Autowired
	private RentService rentService;
	
	@RequestMapping(value = "/rent", method = { RequestMethod.GET })
	public List<RentDTO> getAll(){
		return rentService.findAll();
	}
}
