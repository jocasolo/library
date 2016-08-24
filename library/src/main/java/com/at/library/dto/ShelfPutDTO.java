package com.at.library.dto;

import java.io.Serializable;

public class ShelfPutDTO implements Serializable {

	private static final long serialVersionUID = -6254629839897117753L;

	private Integer id;

	private String description;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

}
