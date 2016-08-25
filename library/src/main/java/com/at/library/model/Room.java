package com.at.library.model;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;

@Entity
public class Room implements Serializable {

	private static final long serialVersionUID = -7112342700691944339L;

	@Id
	@GeneratedValue
	private Integer id;

	private String name;

	private String description;

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "room")
	private List<Bookshelf> shelves;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

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

	public List<Bookshelf> getShelves() {
		return shelves;
	}

	public void setShelves(List<Bookshelf> shelves) {
		this.shelves = shelves;
	}

}
