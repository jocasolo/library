package com.at.library.service.user;

import java.util.List;

import com.at.library.dto.UserDTO;
import com.at.library.model.User;

public interface UserService {

	/**
	 * Realiza la búsqueda de todos los usuarios existentes
	 * @return listado de usuarios
	 */
	public List<UserDTO> findAll();
	
	/**
	 * Transforma un User en UserDTO 
	 * @param user
	 * @return
	 */
	public UserDTO transform(User user);
	
	/**
	 * Transforma un UserDTO en User
	 * @param user
	 * @return
	 */
	public User transform(UserDTO user);
}
