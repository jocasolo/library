package com.at.library.exceptions;

public class BookRentedException extends Exception {

	private static final long serialVersionUID = -505077617238898084L;

	private static final String MSG = "El libro ya se encuentra alquilado.";

	@Override
	public String getMessage() {
		return MSG;
	}

}
