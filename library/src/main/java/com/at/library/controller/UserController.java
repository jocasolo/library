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

import com.at.library.dto.UserDTO;
import com.at.library.dto.UserPutDTO;
import com.at.library.exceptions.UserNotFoundException;
import com.at.library.exceptions.UserWrongUpdateException;
import com.at.library.service.CommonService;
import com.at.library.service.user.UserService;

@RestController
@RequestMapping(value = "/user")
public class UserController {

	@Autowired
	private UserService userService;
	
	@Autowired
	private CommonService commonService;

	private static final Logger log = LoggerFactory.getLogger(UserController.class);

	@RequestMapping(method = { RequestMethod.GET })
	public List<UserDTO> search(
			@RequestParam(value = "dni", required = false) String dni,
			@RequestParam(value = "name", required = false) String name,
			@RequestParam(value = "surname", required = false) String surname,
			@RequestParam(value = "page", required = false, defaultValue = "0") Integer page,
			@RequestParam(value = "size", required = false, defaultValue = "10") Integer size) {
		log.debug(String.format("Buscando usuarios según los campos: %s, %s, %s", dni, name, surname));
		return userService.search(dni, name, surname, new PageRequest(page, size));
	}

	@RequestMapping(method = { RequestMethod.POST })
	public UserDTO create(@RequestBody UserDTO user) {
		log.debug(String.format("Creando el usuario: %s", user));
		return userService.create(user);
	}

	@RequestMapping(value = "/{id}", method = { RequestMethod.GET })
	public UserDTO findOne(@PathVariable("id") Integer id) throws UserNotFoundException {
		log.debug(String.format("Buscando el usuario con id: %s", id));
		return commonService.transform(userService.findOne(id), UserDTO.class);
	}

	@RequestMapping(value = "/{id}", method = { RequestMethod.PUT })
	public void update(
			@PathVariable("id") Integer id, 
			@RequestBody UserPutDTO userDTO) throws UserWrongUpdateException {
		log.debug(String.format("Modificando el usuario: %s", userDTO));
		userService.update(id, userDTO);
	}

	@RequestMapping(value = "/{id}", method = { RequestMethod.DELETE })
	public void delete(@PathVariable("id") Integer id) throws UserNotFoundException {
		log.debug(String.format("Borrando el usuario con el id: %s", id));
		userService.delete(id);
	}
}
