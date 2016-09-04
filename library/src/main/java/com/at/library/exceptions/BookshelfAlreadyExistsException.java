package com.at.library.exceptions;

public class BookshelfAlreadyExistsException extends Exception {

	private static final long serialVersionUID = -5306724629198544016L;
	
	private static final String MSG = "Ya existe una estantería con ese código.";

	@Override
	public String getMessage() {
		return MSG;
	}

}
