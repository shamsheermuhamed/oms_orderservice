package com.shopping.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class RecordNotFoundException extends RuntimeException {
		
		@ExceptionHandler
		public ResponseEntity<Object> exception(RecordNotFoundException ex)
		{
			return new ResponseEntity<>("Record Not Found",HttpStatus.NOT_FOUND);
		}

}
