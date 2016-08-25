package com.at.library.dto;

import java.io.Serializable;
import java.util.List;

public class RoomDTO extends DTO implements Serializable {

	private static final long serialVersionUID = 8900147020824277792L;

	private String name;

	private String description;

	private List<BookshelfDTO> shelves;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public List<BookshelfDTO> getShelves() {
		return shelves;
	}

	public void setShelves(List<BookshelfDTO> shelves) {
		this.shelves = shelves;
	}

	@Override
	public String toString() {
		return "RoomDTO [name=" + name + ", description=" + description + "]";
	}

}
