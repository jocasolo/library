package com.at.library.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Embeddable;
import javax.persistence.FetchType;
import javax.persistence.OneToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Embeddable
public class RentPK implements Serializable {

	private static final long serialVersionUID = 7444334233029230055L;

	@Temporal(TemporalType.TIMESTAMP)
	private Date initDate;

	@OneToOne(fetch = FetchType.LAZY)
	private Book book;

	public Date getInitDate() {
		return initDate;
	}

	public void setInitDate(Date initDate) {
		this.initDate = initDate;
	}

	public Book getBook() {
		return book;
	}

	public void setBook(Book book) {
		this.book = book;
	}

}
