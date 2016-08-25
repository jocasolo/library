package com.at.library.dto;

import java.io.Serializable;

public class EmployeeDTO extends DTO implements Serializable {

	private static final long serialVersionUID = -7059139813298343819L;

	private Integer id;

	private String dni;

	private String name;

	private String surname;

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
		return "EmployeeDTO [id=" + id + ", dni=" + dni + ", name=" + name + ", surname=" + surname + "]";
	}

}
