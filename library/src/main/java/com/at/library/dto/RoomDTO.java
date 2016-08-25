package com.at.library.dto;

import java.io.Serializable;
import java.util.List;

public class RoomDTO extends DTO implements Serializable {

	private static final long serialVersionUID = 8900147020824277792L;

	private String code;

	private List<BookshelfDTO> bookShelves;

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	@Override
	public String toString() {
		return "RoomDTO [code=" + code + "]";
	}

	public List<BookshelfDTO> getBookShelves() {
		return bookShelves;
	}

	public void setBookShelves(List<BookshelfDTO> bookShelves) {
		this.bookShelves = bookShelves;
	}

}
