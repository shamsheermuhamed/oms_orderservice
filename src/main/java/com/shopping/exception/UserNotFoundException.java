package com.shopping.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class UserNotFoundException extends RuntimeException {
		
		@ExceptionHandler
		public ResponseEntity<Object> exception(UserNotFoundException ex)
		{
			return new ResponseEntity<>("User Not Found",HttpStatus.NOT_FOUND);
		}

}