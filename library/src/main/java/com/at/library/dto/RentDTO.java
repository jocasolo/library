package com.at.library.dto;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

public class RentDTO implements Serializable {

	private static final long serialVersionUID = 7364756623634860483L;

	private Integer id;

	private Date startDate;

	private Date returnDate;

	private Date realReturnDate;

	private UserDTO user;

	private UserDTO employee;

	private List<BookDTO> books;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Date getStartDate() {
		return startDate;
	}

	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}

	public Date getReturnDate() {
		return returnDate;
	}

	public void setReturnDate(Date returnDate) {
		this.returnDate = returnDate;
	}

	public Date getRealReturnDate() {
		return realReturnDate;
	}

	public void setRealReturnDate(Date realReturnDate) {
		this.realReturnDate = realReturnDate;
	}

	public UserDTO getUser() {
		return user;
	}

	public void setUser(UserDTO user) {
		this.user = user;
	}

	public List<BookDTO> getBooks() {
		return books;
	}

	public void setBooks(List<BookDTO> books) {
		this.books = books;
	}

	public UserDTO getEmployee() {
		return employee;
	}

	public void setEmployee(UserDTO employee) {
		this.employee = employee;
	}

}
