package com.at.library.controller;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import com.at.library.dto.ApiErrorDTO;
import com.at.library.exceptions.BookNotFoundException;
import com.at.library.exceptions.BookRentedException;
import com.at.library.exceptions.UserNotFoundException;

@ControllerAdvice ( basePackages = { "com.at.library.controller" } )
public class ControllerFails {
	
	@ResponseBody
	@ExceptionHandler(BookRentedException.class)
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	public ApiErrorDTO errorBookRented(Exception e){
		return new ApiErrorDTO(400, e.getMessage());
	}
	
	@ResponseBody
	@ExceptionHandler(BookNotFoundException.class)
	@ResponseStatus(HttpStatus.NOT_FOUND)
	public ApiErrorDTO errorBookNotFound(Exception e){
		return new ApiErrorDTO(404, e.getMessage());
	}
	
	@ResponseBody
	@ExceptionHandler(UserNotFoundException.class)
	@ResponseStatus(HttpStatus.NOT_FOUND)
	public ApiErrorDTO errorUserNotFound(Exception e){
		return new ApiErrorDTO(404, e.getMessage());
	}
	
}
