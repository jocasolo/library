package com.at.library.dto;

import java.util.Date;

import org.dozer.Mapping;

public class HistoryRentedDTO {

	@Mapping(value = "initDate")
	private Date init;

	@Mapping(value = "endDate")
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

	@Mapping(value = "book.title")
	public String getTitle() {
		return title;
	}

	@Mapping(value = "book.title")
	public void setTitle(String title) {
		this.title = title;
	}

	@Mapping(value = "book.id")
	public Integer getIdBook() {
		return idBook;
	}

	@Mapping(value = "book.id")
	public void setIdBook(Integer idBook) {
		this.idBook = idBook;
	}

}
