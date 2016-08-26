package com.at.library.service.user;

import java.util.List;

import com.at.library.dto.UserDTO;
import com.at.library.dto.UserPutDTO;
import com.at.library.model.User;

public interface UserService {

	/**
	 * Realiza la busqueda de todos los usuarios existentes.
	 * 
	 * @return Listado de usuarios.
	 */
	List<UserDTO> findAll();

	/**
	 * Realiza la búsqueda de un usuario por id.
	 * 
	 * @param id
	 * @return Usuario correspondiente al id buscado.
	 */
	User findOne(Integer id);

	/**
	 * Crea un nuevo usuario.
	 * 
	 * @param userDTO
	 * @return El usuario creado.
	 */
	UserDTO create(UserDTO userDto);

	/**
	 * Actualiza un usuario.
	 * 
	 * @param user
	 */
	void update(UserPutDTO userDto);

	/**
	 * Elimina el usuario correspondiente al id dado.
	 * 
	 * @param id
	 */
	void delete(Integer id);

	/**
	 * Transforma un User en un UserDTO
	 * 
	 * @param user
	 * @return
	 */
	UserDTO transform(User user);

	/**
	 * Transforma un DTO en un objeto User. 
	 * @param user
	 * @return
	 */
	<T> User transform(T userDto);
}
