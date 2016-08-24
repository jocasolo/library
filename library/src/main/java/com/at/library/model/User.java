package com.at.library.model;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import com.at.library.enums.UserEnum;

@Entity
public class User implements Serializable {

	private static final long serialVersionUID = 4877910540692673267L;

	@Id
	@GeneratedValue
	private Integer id;

	private String dni;

	private String name;

	private String surname;

	@Enumerated(EnumType.STRING)
	private UserEnum type;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "address_id")
	private Address address;

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "user")
	private List<Rent> rents;

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

	public Address getAddress() {
		return address;
	}

	public void setAddress(Address address) {
		this.address = address;
	}

	public List<Rent> getRents() {
		return rents;
	}

	public void setRents(List<Rent> rents) {
		this.rents = rents;
	}

	public UserEnum getType() {
		return type;
	}

	public void setType(UserEnum type) {
		this.type = type;
	}

}
