package com.at.library.service.rent;

import java.util.Date;
import java.util.List;

import org.springframework.data.domain.Pageable;

import com.at.library.dto.HistoryRentedDTO;
import com.at.library.dto.RentDTO;
import com.at.library.dto.RentPostDTO;
import com.at.library.exceptions.BookNotFoundException;
import com.at.library.exceptions.BookNotRentedException;
import com.at.library.exceptions.BookRentedException;
import com.at.library.exceptions.EmployeeNotFoundException;
import com.at.library.exceptions.UserBannedException;
import com.at.library.exceptions.UserNotFoundException;
import com.at.library.model.Rent;

public interface RentService {

	/**
	 * Realiza la busqueda de todos los alquileres existentes
	 * 
	 * @param pageable
	 * @return Listado con el historial de alquileres.
	 */
	List<HistoryRentedDTO> findAll(Pageable pageable);

	/**
	 * Realiza el alquiler de un libro a un determinado usario y realizado por
	 * un determinado empleado.
	 *
	 * @param rentDto
	 * @return Alquiler creado.
	 * @throws BookNotFoundException
	 * @throws BookRentedException
	 * @throws UserBannedException
	 * @throws UserNotFoundException
	 * @throws EmployeeNotFoundException
	 */
	RentDTO create(RentPostDTO rentDto) throws BookNotFoundException, BookRentedException, UserBannedException,
			UserNotFoundException, EmployeeNotFoundException;

	/**
	 * Realiza la devoluci�n de un libro que pertenece a un determinado
	 * alquiler.
	 * 
	 * @param idBook
	 * @return Alquiler devuelto.
	 * @throws BookNotFoundException
	 * @throws BookNotRentedException
	 */
	void restore(Integer idBook) throws BookNotFoundException, BookNotRentedException;

	/**
	 * Calcula la fecha en que se debe devolver un libro dada la inicial.
	 * 
	 * @param initDate
	 * @return Fecha de devoluci�n.
	 */
	Date calcReturnDate(Date initDate);

	/**
	 * Busca todos los alquileres que a�n no se han devuelto y que la fecha en
	 * que deber�a haberse hecho ya ha pasado.
	 * 
	 * @return Listado con el historial de alquileres retrasados.
	 */
	List<Rent> findSanctionable();

	/**
	 * Obtiene el historial de alquileres de un libro.
	 * 
	 * @param idBook
	 * @return Listado con el historial de alquileres de un libro.
	 */
	List<HistoryRentedDTO> getBookHistory(Integer idBook, Pageable pageable);

	/**
	 * Obtiene el historial de alquileres de un usuario.
	 * 
	 * @param idUser
	 * @return Listado con el historial de alquileres de un usuario.
	 */
	List<HistoryRentedDTO> getUserHistory(Integer idUser, Pageable pageable);

}
