package com.at.library.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.at.library.dto.RoomDTO;
import com.at.library.service.room.RoomService;

@RestController
public class RoomController {
	
	@Autowired
	private RoomService roomService;
	
	@RequestMapping(value = "/room", method = { RequestMethod.GET })
	public List<RoomDTO> getAll(){
		return roomService.findAll();
	}
}
