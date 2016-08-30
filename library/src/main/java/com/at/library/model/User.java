package com.at.library.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

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
	private UserEnum status;

	@Temporal(TemporalType.DATE)
	private Date penalizeDate;

	@Temporal(TemporalType.DATE)
	private Date forgiveDate;

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

	public UserEnum getStatus() {
		return status;
	}

	public void setStatus(UserEnum status) {
		this.status = status;
	}

	public Date getPenalizeDate() {
		return penalizeDate;
	}

	public void setPenalizeDate(Date penalizeDate) {
		this.penalizeDate = penalizeDate;
	}

	public Date getForgiveDate() {
		return forgiveDate;
	}

	public void setForgiveDate(Date forgiveDate) {
		this.forgiveDate = forgiveDate;
	}

}
