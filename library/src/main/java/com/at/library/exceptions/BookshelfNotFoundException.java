package com.at.library.exceptions;

public class BookshelfNotFoundException extends Exception {

	private static final long serialVersionUID = 4136412224839654374L;

	private static final String MSG = "No se ha encontrado ninguna estantería con el Id introducido.";

	@Override
	public String getMessage() {
		return MSG;
	}

}
