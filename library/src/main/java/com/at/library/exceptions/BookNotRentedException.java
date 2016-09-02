package com.at.library.exceptions;

public class BookNotRentedException extends Exception {

	private static final long serialVersionUID = 2876940792567199389L;

	private static final String MSG = "El libro introducido no se encuentra en alquiler.";

	@Override
	public String getMessage() {
		return MSG;
	}

}
