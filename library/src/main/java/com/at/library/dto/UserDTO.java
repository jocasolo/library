package com.at.library.dto;

import java.io.Serializable;

public class UserDTO implements Serializable {

	private static final long serialVersionUID = 3827455324138571715L;

	private String dni;

	private String name;

	private String surname;

	public String getDni() {
		return dni;
	}

	public void setDni(String dni) {
		this.dni = dni;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getSurname() {
		return surname;
	}

	public void setSurname(String surname) {
		this.surname = surname;
	}
	
	
}
