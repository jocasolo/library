package com.at.library.dto;

import java.io.Serializable;

import com.at.library.enums.BookEnum;

public class BookDTO extends DTO implements Serializable {

	private static final long serialVersionUID = 1583585532736761521L;

	private Integer id;

	private String isbn;

	private String title;

	private String author;

	private BookEnum status;

	public BookDTO(Integer id, String isbn, String title, String author) {
		super();
		this.id = id;
		this.isbn = isbn;
		this.title = title;
		this.author = author;
	}

	public BookDTO() {
		super();
	}

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

	public BookEnum getStatus() {
		return status;
	}

	public void setStatus(BookEnum status) {
		this.status = status;
	}

	@Override
	public String toString() {
		return "BookDTO [id=" + id + ", title=" + title + "]";
	}

}
