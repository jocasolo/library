package com.at.library.dto;

import java.io.Serializable;
import java.util.Date;

public class RentDTO extends DTO implements Serializable {

	private static final long serialVersionUID = 7364756623634860483L;

	private Date initDate;

	private Date endDate;

	private Date returnDate;

	private UserDTO user;

	private EmployeeDTO employee;

	private BookDTO book;

	public Date getReturnDate() {
		return returnDate;
	}

	public void setReturnDate(Date returnDate) {
		this.returnDate = returnDate;
	}

	public UserDTO getUser() {
		return user;
	}

	public void setUser(UserDTO user) {
		this.user = user;
	}

	public BookDTO getBook() {
		return book;
	}

	public void setBook(BookDTO book) {
		this.book = book;
	}

	public Date getInitDate() {
		return initDate;
	}

	public void setInitDate(Date initDate) {
		this.initDate = initDate;
	}

	public Date getEndDate() {
		return endDate;
	}

	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}
	
	public EmployeeDTO getEmployee() {
		return employee;
	}

	public void setEmployee(EmployeeDTO employee) {
		this.employee = employee;
	}

	@Override
	public String toString() {
		return "RentDTO [book=" + book + ", initDate=" + initDate + ", endDate=" + endDate + "]";
	}

}
