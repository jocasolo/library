package com.at.library.dto;

import java.io.Serializable;
import java.util.List;

public class RoomDTO implements Serializable {

	private static final long serialVersionUID = 8900147020824277792L;
	
	private String name;
	
	private String description;
	
	private List<ShelfDTO> shelves;

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

	public List<ShelfDTO> getShelves() {
		return shelves;
	}

	public void setShelves(List<ShelfDTO> shelves) {
		this.shelves = shelves;
	}

}
