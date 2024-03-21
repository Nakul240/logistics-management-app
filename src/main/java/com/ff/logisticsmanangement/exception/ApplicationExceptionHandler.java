package com.ff.logisticsmanangement.exception;



import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.ff.logisticsmanangement.dto.ResponseStructure;

@ControllerAdvice
public class ApplicationExceptionHandler extends ResponseEntityExceptionHandler{

	@ExceptionHandler(IndexOutOfBoundsException.class)
	public ResponseEntity<ResponseStructure<String>> catchIndexOutOfBoundsException(IndexOutOfBoundsException exception){
		ResponseStructure<String> structure = new ResponseStructure<String>();
		structure.setStatusCode(HttpStatus.BAD_REQUEST.value());
		structure.setMessage("Bad Request");
		structure.setData(exception.getMessage());
		return new ResponseEntity<ResponseStructure<String>>(structure, HttpStatus.BAD_REQUEST);
	}
	
	@ExceptionHandler(DateMismatchException.class)
	public ResponseEntity<ResponseStructure<String>> catchDateMismatchException(DateMismatchException exception){
		ResponseStructure<String> structure = new ResponseStructure<String>();
		structure.setStatusCode(HttpStatus.BAD_REQUEST.value());
		structure.setMessage("Bad Request");
		structure.setData(exception.getMessage());
		return new ResponseEntity<ResponseStructure<String>>(structure, HttpStatus.BAD_REQUEST);
	}
	
	@ExceptionHandler(AccessDeniedException.class)
	public ResponseEntity<ResponseStructure<String>> handleAuthException(AccessDeniedException exception){
		
		ResponseStructure<String> structure = new ResponseStructure<String>();
		structure.setStatusCode(HttpStatus.FORBIDDEN.value());
		structure.setMessage("Bad Request");
		structure.setData("You are not authorized to perform particular operation!");
		return new ResponseEntity<ResponseStructure<String>>(structure, HttpStatus.FORBIDDEN);
		
	}
}
