package com.at.library.service.address;

import java.util.List;

import com.at.library.dto.AddressDTO;
import com.at.library.model.Address;

public interface AddressService {

	/**
	 * Realiza la busqueda de todos las direcciones existentes
	 * 
	 * @return listado de direcciones
	 */
	List<AddressDTO> findAll();

	/**
	 * Transforma un Address en un AdressDTO
	 * 
	 * @param book
	 * @return
	 */
	AddressDTO transform(Address address);

	/**
	 * Transforma un AddressDTO en un Address
	 * 
	 * @param book
	 * @return
	 */
	Address transform(AddressDTO address);

}
