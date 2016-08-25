package com.at.library.dto;

import java.io.Serializable;
import java.util.List;

public class BookshelfDTO extends DTO implements Serializable {

	private static final long serialVersionUID = 1769863489245854198L;

	private String code;

	private List<BookDTO> books;

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public List<BookDTO> getBooks() {
		return books;
	}

	public void setBooks(List<BookDTO> books) {
		this.books = books;
	}

	@Override
	public String toString() {
		return "ShelfDTO [code=" + code + "]";
	}

}
