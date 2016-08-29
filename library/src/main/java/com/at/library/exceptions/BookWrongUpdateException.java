package com.at.library.exceptions;

public class BookWrongUpdateException extends Exception {

	private static final long serialVersionUID = 6417379501040003621L;
	
	private static final String MSG = "El Id introducido en el cuerpo no concuerda con el de la petición.";
	
	@Override
	public String getMessage() {
		return MSG;
	}

}
