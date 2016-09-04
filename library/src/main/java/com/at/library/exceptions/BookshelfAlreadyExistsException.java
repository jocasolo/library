package com.at.library.exceptions;

public class BookshelfAlreadyExistsException extends Exception {

	private static final long serialVersionUID = -5306724629198544016L;
	
	private static final String MSG = "Ya existe una estanter�a con ese c�digo.";

	@Override
	public String getMessage() {
		return MSG;
	}

}
