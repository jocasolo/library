package com.at.library.dto;

import java.io.Serializable;

import com.at.library.enums.UserEnum;

public class UserDTO extends DTO implements Serializable {

	private static final long serialVersionUID = 3827455324138571715L;

	private String dni;

	private String name;

	private String surname;

	private UserEnum type;

	private AddressDTO address;

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

	public AddressDTO getAddress() {
		return address;
	}

	public void setAddress(AddressDTO address) {
		this.address = address;
	}

	public UserEnum getType() {
		return type;
	}

	public void setType(UserEnum type) {
		this.type = type;
	}

	@Override
	public String toString() {
		return "UserDTO [dni=" + dni + ", name=" + name + ", surname=" + surname + ", type=" + type + "]";
	}

}
