package com.at.library.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.at.library.dto.BookDTO;
import com.at.library.service.book.BookService;

@RestController
public class BookController {

	@Autowired
	private BookService bookservice;

	@RequestMapping(value = "/book", method = { RequestMethod.GET })
	public List<BookDTO> getAll() {
		return bookservice.findAll();
	}

}
