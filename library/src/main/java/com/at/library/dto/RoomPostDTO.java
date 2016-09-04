package com.at.library.dto;

import java.io.Serializable;

public class RoomPostDTO extends DTO implements Serializable {

	private static final long serialVersionUID = 4478853253718100921L;

	private String code;

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	@Override
	public String toString() {
		return "RoomPostDTO [code=" + code + "]";
	}

}
