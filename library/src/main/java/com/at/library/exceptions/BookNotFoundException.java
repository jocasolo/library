package com.at.library.exceptions;

public class BookNotFoundException extends Exception {

	private static final long serialVersionUID = 1644583033208122457L;

	private static final String MSG = "No se ha encontrado ningún libro con el Id introducido.";

	@Override
	public String getMessage() {
		return MSG;
	}

}
