package com.at.library.dto;

import java.io.Serializable;

public class RoomPutDTO extends DTO implements Serializable {

	private static final long serialVersionUID = 3734421142709054469L;

	private String code;

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	@Override
	public String toString() {
		return "RoomPutDTO [code=" + code + "]";
	}

}
