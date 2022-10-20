package com.shopping.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class ProductOutOfStockException extends RuntimeException {
		
		
		@ExceptionHandler
		public ResponseEntity<Object> exception(ProductOutOfStockException ex)
		{
			return new ResponseEntity<>("Inventory Out Of stock",HttpStatus.NOT_FOUND);
		}
}