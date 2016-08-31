package com.at.library.service.rent;

import java.util.Date;
import java.util.List;

import com.at.library.dto.HistoryRentedDTO;
import com.at.library.dto.RentDTO;
import com.at.library.dto.RentPostDTO;
import com.at.library.exceptions.BookNotFoundException;
import com.at.library.exceptions.BookRentedException;
import com.at.library.exceptions.UserBannedException;
import com.at.library.model.Rent;

public interface RentService {

	/**
	 * Realiza la busqueda de todos los alquileres existentes
	 * 
	 * @return listado de alquileres
	 */
	List<RentDTO> findAll();
	
	/**
	 * Realiza el alquiler de un libro a un determinado usario y realizado
	 * por un determinado empleado.
	 *
	 * @param idBook
	 * @param rentDto
	 * @return
	 * @throws BookNotFoundException
	 * @throws BookRentedException
	 * @throws UserBannedException 
	 */
	RentDTO create(Integer idBook, RentPostDTO rentDto) throws BookNotFoundException, BookRentedException, UserBannedException;
	
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
	
	/**
	 * Busca todos los alquileres que aún no se han devuelto y que la fecha en que debería haberse
	 * hecho ya ha pasado.
	 * 
	 * @return
	 */
	List<Rent> findSanctionable();

	/**
	 * Obtiene el historial de alquileres de un libro.
	 * 
	 * @param idBook
	 * @return
	 */
	List<HistoryRentedDTO> getBookHistory(Integer idBook);
	
	/**
	 * Obtiene el historial de alquileres de un usuario.
	 * 
	 * @param idUser
	 * @return
	 */
	List<HistoryRentedDTO> getUserHistory(Integer idUser);
	
	/**
	 * Transforma un Rent en un RentDTO
	 * 
	 * @param rent
	 * @return
	 */
	<T> T transform(Rent rent);
	
	public <T> List<T> transform(List<Rent> rents, Class<T> destinationClass);


}
