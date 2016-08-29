package com.at.library.exceptions;

public class UserNotFoundException extends Exception {

	private static final long serialVersionUID = -5906268299384526416L;

	private static final String MSG = "No se ha encontrado ningún libro con el Id introducido.";

	@Override
	public String getMessage() {
		return MSG;
	}
}
