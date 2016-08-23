package com.at.library.dto;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import com.at.library.model.Book;
import com.at.library.model.User;

public class RentDTO implements Serializable {

	private static final long serialVersionUID = 7364756623634860483L;
	
	private User user;
	
	private List<Book> books;
	
	private Date startDate;
	
	private Date returnDate;
	
	private Date realReturnDate;

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public List<Book> getBooks() {
		return books;
	}

	public void setBooks(List<Book> books) {
		this.books = books;
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

}
