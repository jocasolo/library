package com.at.library.exceptions;

public class UserWrongUpdateException extends Exception {

	private static final long serialVersionUID = -5583724768680766059L;

	private static final String MSG = "El Id introducido en el cuerpo no concuerda con el de la petición.";

	@Override
	public String getMessage() {
		return MSG;
	}

}
