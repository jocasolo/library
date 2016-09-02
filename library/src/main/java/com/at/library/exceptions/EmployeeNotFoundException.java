package com.at.library.exceptions;

public class EmployeeNotFoundException extends Exception {

	private static final long serialVersionUID = 1969511977784301117L;

	private static final String MSG = "No se ha encontrado ningún empleado con el Id introducido.";

	@Override
	public String getMessage() {
		return MSG;
	}

}
