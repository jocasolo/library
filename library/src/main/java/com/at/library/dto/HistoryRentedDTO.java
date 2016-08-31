package com.at.library.dto;

import java.util.Date;

public class HistoryRentedDTO {
	
	private Date init;
	
	private Date end;
	
	private String title;
	
	private Integer idBook;
	
	public Date getInit() {
		return init;
	}
	public void setInit(Date init) {
		this.init = init;
	}
	public Date getEnd() {
		return end;
	}
	public void setEnd(Date end) {
		this.end = end;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public Integer getIdBook() {
		return idBook;
	}
	public void setIdBook(Integer idBook) {
		this.idBook = idBook;
	}
	
}
