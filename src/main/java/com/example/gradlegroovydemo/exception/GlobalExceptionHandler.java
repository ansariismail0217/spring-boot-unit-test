package com.example.gradlegroovydemo.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

@ControllerAdvice
public class GlobalExceptionHandler {
	
	@ExceptionHandler(value = UserAlreadyExistsException.class)
	public @ResponseBody ErrorResponse handleUserAlreadyExistsException(UserAlreadyExistsException ex) {
		return new ErrorResponse(HttpStatus.CONFLICT.toString(), ex.getMessage());
	}
	
	@ExceptionHandler(value = NoSuchUserExistsException.class)
	public @ResponseBody ErrorResponse handleNoSuchUserExistsException(NoSuchUserExistsException ex) {
		return new ErrorResponse(HttpStatus.NOT_FOUND.toString(), ex.getMessage());
	}

}
