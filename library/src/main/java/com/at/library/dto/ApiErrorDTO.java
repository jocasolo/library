package com.at.library.dto;

public class ApiErrorDTO extends DTO {

	private static final long serialVersionUID = 1593249081966979650L;

	private Integer code;

	private String msg;
	
	public ApiErrorDTO(Integer code, String msg){
		this.code = code;
		this.msg = msg;
	}

	public Integer getCode() {
		return code;
	}

	public void setCode(Integer code) {
		this.code = code;
	}

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}

	@Override
	public String toString() {
		return "ApiErrorDTO [code=" + code + ", msg=" + msg + "]";
	}

}
