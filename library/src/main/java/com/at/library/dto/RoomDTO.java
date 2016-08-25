package com.at.library.dto;

import java.io.Serializable;
import java.util.List;

public class RoomDTO extends DTO implements Serializable {

	private static final long serialVersionUID = 8900147020824277792L;

	private String code;

	private List<BookshelfDTO> shelves;

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public List<BookshelfDTO> getShelves() {
		return shelves;
	}

	public void setShelves(List<BookshelfDTO> shelves) {
		this.shelves = shelves;
	}

	@Override
	public String toString() {
		return "RoomDTO [code=" + code + "]";
	}

}
