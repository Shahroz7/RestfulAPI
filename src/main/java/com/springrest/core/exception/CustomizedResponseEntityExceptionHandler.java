package com.springrest.core.exception;

import java.util.Date;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.springrest.core.user.UserNotFoundException;

@ControllerAdvice
@RestController
public class CustomizedResponseEntityExceptionHandler extends ResponseEntityExceptionHandler{
	
    @ExceptionHandler(Exception.class) 
	public final ResponseEntity<Object> handleAllException(Exception ex, WebRequest request) throws Exception {
    	ExceptionResponse expResponse = new ExceptionResponse(new Date(), ex.getMessage(), request.getDescription(false));
         return new ResponseEntity(expResponse, HttpStatus.INTERNAL_SERVER_ERROR);
    }
    
    @ExceptionHandler(UserNotFoundException.class) 
	public final ResponseEntity<Object> handleUserNotFoundException(UserNotFoundException ex, WebRequest request) throws Exception {
    	ExceptionResponse expResponse = new ExceptionResponse(new Date(), ex.getMessage(), request.getDescription(false));
         return new ResponseEntity(expResponse, HttpStatus.NOT_FOUND);
    }
    
    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(
			MethodArgumentNotValidException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
    	ExceptionResponse expResponse = new ExceptionResponse(new Date(), ex.getMessage(), ex.getBindingResult().toString());
    	return new ResponseEntity(expResponse, HttpStatus.BAD_REQUEST);
	}
	

}
