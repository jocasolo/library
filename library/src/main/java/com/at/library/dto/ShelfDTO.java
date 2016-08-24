package com.at.library.dto;

import java.io.Serializable;
import java.util.List;

public class ShelfDTO extends DTO implements Serializable {

	private static final long serialVersionUID = 1769863489245854198L;

	private String description;

	private List<BookDTO> books;

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public List<BookDTO> getBooks() {
		return books;
	}

	public void setBooks(List<BookDTO> books) {
		this.books = books;
	}

	@Override
	public String toString() {
		return "ShelfDTO [description=" + description + "]";
	}

}
