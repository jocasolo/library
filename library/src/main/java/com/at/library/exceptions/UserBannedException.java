package com.at.library.exceptions;

public class UserBannedException extends Exception {

	private static final long serialVersionUID = 683358846987371334L;
	
	private static final String MSG = "El usuario está sancionado.";

	@Override
	public String getMessage() {
		return MSG;
	}
	
}
