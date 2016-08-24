package com.at.library.dto;

import java.io.Serializable;

public class UserPutDTO extends DTO implements Serializable {

	private static final long serialVersionUID = 8070095497224083223L;

	private Integer id;

	private String dni;

	private String name;

	private String surname;
	
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

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public AddressDTO getAddress() {
		return address;
	}

	public void setAddress(AddressDTO address) {
		this.address = address;
	}

	@Override
	public String toString() {
		return "UserPutDTO [id=" + id + ", dni=" + dni + ", name=" + name + ", surname=" + surname + "]";
	}

}
