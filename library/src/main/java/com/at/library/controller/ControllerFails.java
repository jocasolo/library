package com.at.library.controller;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import com.at.library.dto.ApiErrorDTO;
import com.at.library.exceptions.BookNotFoundException;
import com.at.library.exceptions.BookNotRentedException;
import com.at.library.exceptions.BookRentedException;
import com.at.library.exceptions.BookWrongUpdateException;
import com.at.library.exceptions.BookshelfNotFoundException;
import com.at.library.exceptions.EmployeeNotFoundException;
import com.at.library.exceptions.UserBannedException;
import com.at.library.exceptions.UserNotFoundException;
import com.at.library.exceptions.UserWrongUpdateException;

@ControllerAdvice ( basePackages = { "com.at.library.controller" } )
public class ControllerFails {
	
	@ResponseBody
	@ExceptionHandler(BookRentedException.class)
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	public ApiErrorDTO error(BookRentedException e){
		return new ApiErrorDTO(400, e.getMessage());
	}
	
	@ResponseBody
	@ExceptionHandler(BookNotFoundException.class)
	@ResponseStatus(HttpStatus.NOT_FOUND)
	public ApiErrorDTO error(BookNotFoundException e){
		return new ApiErrorDTO(404, e.getMessage());
	}
	
	@ResponseBody
	@ExceptionHandler(BookNotRentedException.class)
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	public ApiErrorDTO error(BookNotRentedException e){
		return new ApiErrorDTO(400, e.getMessage());
	}
	
	@ResponseBody
	@ExceptionHandler(UserWrongUpdateException.class)
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	public ApiErrorDTO error(UserWrongUpdateException e){
		return new ApiErrorDTO(400, e.getMessage());
	}
	
	@ResponseBody
	@ExceptionHandler(UserNotFoundException.class)
	@ResponseStatus(HttpStatus.NOT_FOUND)
	public ApiErrorDTO error(UserNotFoundException e){
		return new ApiErrorDTO(404, e.getMessage());
	}
	
	@ResponseBody
	@ExceptionHandler(UserBannedException.class)
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	public ApiErrorDTO error(UserBannedException e){
		return new ApiErrorDTO(400, e.getMessage());
	}
	
	@ResponseBody
	@ExceptionHandler(BookWrongUpdateException.class)
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	public ApiErrorDTO error(BookWrongUpdateException e){
		return new ApiErrorDTO(400, e.getMessage());
	}
	
	@ResponseBody
	@ExceptionHandler(EmployeeNotFoundException.class)
	@ResponseStatus(HttpStatus.NOT_FOUND)
	public ApiErrorDTO error(EmployeeNotFoundException e){
		return new ApiErrorDTO(404, e.getMessage());
	}
	
	@ResponseBody
	@ExceptionHandler(BookshelfNotFoundException.class)
	@ResponseStatus(HttpStatus.NOT_FOUND)
	public ApiErrorDTO error(BookshelfNotFoundException e){
		return new ApiErrorDTO(404, e.getMessage());
	}
	
}
