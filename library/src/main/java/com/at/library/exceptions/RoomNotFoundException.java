package com.at.library.exceptions;

public class RoomNotFoundException extends Exception {

	private static final long serialVersionUID = 1283234175731713493L;
	
	private static final String MSG = "No se ha encontrado ninguna sala con el codigo introducido.";

	@Override
	public String getMessage() {
		return MSG;
	}
}
