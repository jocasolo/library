package com.at.library.dto;

public class RentPostDTO extends DTO {

	private static final long serialVersionUID = -3488560761627120367L;
	
	private Integer book;

	private Integer user;

	private Integer employee;

	public Integer getBook() {
		return book;
	}

	public void setBook(Integer book) {
		this.book = book;
	}

	public Integer getUser() {
		return user;
	}

	public void setUser(Integer user) {
		this.user = user;
	}

	public Integer getEmployee() {
		return employee;
	}

	public void setEmployee(Integer employee) {
		this.employee = employee;
	}

	@Override
	public String toString() {
		return "RentPostDTO [book=" + book + ", user=" + user + ", employee=" + employee + "]";
	}
	
}
