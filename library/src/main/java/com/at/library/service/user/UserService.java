package com.at.library.service.user;

import java.util.List;

import org.springframework.data.domain.Pageable;

import com.at.library.dto.UserDTO;
import com.at.library.dto.UserPutDTO;
import com.at.library.exceptions.UserNotFoundException;
import com.at.library.exceptions.UserWrongUpdateException;
import com.at.library.model.User;

public interface UserService {

	/**
	 * Realiza la busqueda de todos los usuarios existentes.
	 * 
	 * @return Listado de usuarios.
	 */
	List<UserDTO> findAll();

	/**
	 * Busca todos los usuarios que concuerden con los campos de b�squeda.
	 * 
	 * @param dni
	 * @param name
	 * @param surname
	 * @return Listado de usuarios que concuerdan con los par�metros de b�squeda.
	 */
	List<UserDTO> search(String dni, String name, String surname, Pageable pageable);

	/**
	 * Realiza la b�squeda de un usuario por id.
	 * 
	 * @param id
	 * @return Usuario correspondiente al id buscado.
	 * @throws UserNotFoundException
	 */
	User findOne(Integer id) throws UserNotFoundException;

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
	 * @param id
	 * @param userDto
	 * @throws UserWrongUpdateException
	 */
	void update(Integer id, UserPutDTO userDto) throws UserWrongUpdateException;

	/**
	 * Elimina el usuario correspondiente al id dado.
	 * 
	 * @param id
	 * @throws UserNotFoundException
	 */
	void delete(Integer id) throws UserNotFoundException;

	/**
	 * Comprueba los usuarios a sancionar y los sanciona.
	 */
	void penalize();

	/**
	 * Perdona a los usuarios
	 */
	void forgive();

	/**
	 * Comprueba si un usuario puede alquilar libros.
	 * 
	 * @param user
	 * @return Si el usuario est� sancionado o no.
	 */
	Boolean isBanned(User user);
}
