package com.at.library.dto;

public class RentPostDTO extends DTO {

	private static final long serialVersionUID = -3488560761627120367L;

	private Integer idUser;

	private Integer idEmployee;

	public Integer getIdUser() {
		return idUser;
	}

	public void setIdUser(Integer idUser) {
		this.idUser = idUser;
	}

	public Integer getIdEmployee() {
		return idEmployee;
	}

	public void setIdEmployee(Integer idEmployee) {
		this.idEmployee = idEmployee;
	}

	@Override
	public String toString() {
		return "RentPostDTO [idUser=" + idUser + ", idEmployee=" + idEmployee + "]";
	}

}
