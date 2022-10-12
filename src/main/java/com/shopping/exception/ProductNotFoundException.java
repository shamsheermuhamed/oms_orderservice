package com.shopping.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class ProductNotFoundException extends RuntimeException {
		
		@ExceptionHandler
		public ResponseEntity<Object> exception(ProductNotFoundException ex)
		{
			return new ResponseEntity<>("Product Not Found",HttpStatus.NOT_FOUND);
		}
}