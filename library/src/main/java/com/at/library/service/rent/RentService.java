package com.at.library.service.rent;

import java.util.List;

import com.at.library.dto.BookDTO;
import com.at.library.dto.EmployeeDTO;
import com.at.library.dto.RentDTO;
import com.at.library.dto.UserDTO;
import com.at.library.model.Rent;

public interface RentService {

	/**
	 * Realiza la busqueda de todos los alquileres existentes
	 * 
	 * @return listado de alquileres
	 */
	List<RentDTO> findAll();

	/**
	 * Transforma un Rent en un RentDTO
	 * 
	 * @param rent
	 * @return
	 */
	RentDTO transform(Rent rent);

	/**
	 * Transforma un RentDTO en un Rent
	 * 
	 * @param rent
	 * @return
	 */
	Rent transform(RentDTO rent);
	
	/**
	 * Realiza el alquiler de un libro a un determinado usario y realizado
	 * por un determinado empleado.
	 * 
	 * @param book
	 * @param user
	 * @param employee
	 * @return
	 */
	RentDTO rentBook(BookDTO book, UserDTO user, EmployeeDTO employee);

}
