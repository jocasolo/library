package com.at.library.dto;

import java.io.Serializable;

public class BookshelfPostDTO extends DTO implements Serializable {

	private static final long serialVersionUID = -8822627866757727591L;

	private String code;

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	@Override
	public String toString() {
		return "BookshelfPostDTO [code=" + code + "]";
	}

}
