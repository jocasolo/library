package com.at.library.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.at.library.dto.AddressDTO;
import com.at.library.service.address.AddressService;

@RestController
public class AddressController {
	
	@Autowired
	private AddressService addressService;
	
	@RequestMapping(value = "/address", method = { RequestMethod.GET })
	public List<AddressDTO> getAll() {
		return addressService.findAll();
	}
}
