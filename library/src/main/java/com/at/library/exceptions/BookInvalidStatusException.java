package com.at.library.exceptions;

public class BookInvalidStatusException extends Exception {

	private static final long serialVersionUID = -3600428240746648316L;
	
	private static final String MSG = "El estado del libro introducido no es válido.";

	@Override
	public String getMessage() {
		return MSG;
	}
}
