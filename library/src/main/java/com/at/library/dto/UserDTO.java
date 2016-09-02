package com.at.library.dto;

import java.io.Serializable;

public class UserDTO extends DTO implements Serializable {

	private static final long serialVersionUID = 3827455324138571715L;

	private Integer id;

	private String dni;

	private String name;

	private String surname;

	public UserDTO(Integer id, String dni, String name, String surname) {
		super();
		this.id = id;
		this.dni = dni;
		this.name = name;
		this.surname = surname;
	}

	public UserDTO() {
		super();
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

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

	@Override
	public String toString() {
		return "UserDTO [dni=" + dni + ", name=" + name + ", surname=" + surname + "]";
	}

}
