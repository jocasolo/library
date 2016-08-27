package com.at.library.dto;

public class RentReturnDTO extends DTO {

	private static final long serialVersionUID = -5550961895423460680L;

	private Integer idBook;

	public Integer getIdBook() {
		return idBook;
	}

	public void setIdBook(Integer idBook) {
		this.idBook = idBook;
	}

	@Override
	public String toString() {
		return "RentReturnDTO [idBook=" + idBook + "]";
	}

}
