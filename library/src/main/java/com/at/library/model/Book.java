package com.at.library.model;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.at.library.enums.StatusEnum;

@Entity
public class Book implements Serializable {

	private static final long serialVersionUID = 6374272004167410735L;

	@Id
	@GeneratedValue
	private Integer id;

	private String isbn;

	private String title;

	private String author;

	@Enumerated(EnumType.STRING)
	private StatusEnum status;

	@Temporal(TemporalType.DATE)
	private Date startDate;

	@ManyToMany(fetch = FetchType.LAZY, mappedBy = "books")
	private List<Rent> rents;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "shelf_id")
	private Bookshelf shelf;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getIsbn() {
		return isbn;
	}

	public void setIsbn(String isbn) {
		this.isbn = isbn;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getAuthor() {
		return author;
	}

	public void setAuthor(String author) {
		this.author = author;
	}

	public StatusEnum getStatus() {
		return status;
	}

	public void setStatus(StatusEnum status) {
		this.status = status;
	}

	public Date getStartDate() {
		return startDate;
	}

	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}

	public List<Rent> getRents() {
		return rents;
	}

	public void setRents(List<Rent> rents) {
		this.rents = rents;
	}
	
	public Bookshelf getShelf() {
		return shelf;
	}

	public void setShelf(Bookshelf shelf) {
		this.shelf = shelf;
	}

}
