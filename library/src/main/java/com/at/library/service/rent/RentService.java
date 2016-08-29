package com.at.library.service.rent;

import java.util.Date;
import java.util.List;

import com.at.library.dto.RentDTO;
import com.at.library.dto.RentPostDTO;
import com.at.library.exceptions.BookNotFoundException;
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
	 * @param idBook
	 * @param idUser
	 * @param idEmployee
	 * @return
	 * @throws BookNotFoundException 
	 */
	RentDTO create(RentPostDTO rentDto) throws BookNotFoundException;
	
	/**
	 * Realiza la devolución de un libro que pertenece a un determinado alquiler.
	 * 
	 * @param idBook
	 * @return
	 * @throws BookNotFoundException 
	 */
	RentDTO restore(Integer idBook) throws BookNotFoundException;
	
	/**
	 * Calcula la fecha en que se debe devolver un libro dada la inicial.
	 * 
	 * @param initDate
	 * @return
	 */
	Date calcEndDate(Date initDate);

}
