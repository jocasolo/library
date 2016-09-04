package com.at.library.exceptions;

public class RoomAlreadyExistsException extends Exception {
	private static final long serialVersionUID = 2049682349551035215L;
	
	private static final String MSG = "Ya existe una sala con ese código.";

	@Override
	public String getMessage() {
		return MSG;
	}

}
