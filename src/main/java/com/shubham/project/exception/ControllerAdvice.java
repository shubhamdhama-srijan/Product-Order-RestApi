package com.shubham.project.exception;

import java.util.HashMap;
import java.util.Map;
import java.util.NoSuchElementException;

import javax.management.AttributeNotFoundException;

import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@RestControllerAdvice
public class ControllerAdvice extends ResponseEntityExceptionHandler{

	@ExceptionHandler(DataIntegrityViolationException.class)
	public ResponseEntity<Object> handleDataIntegrityViolation(DataIntegrityViolationException dataIntegrityViolationException) {
		return new ResponseEntity<Object>("Attempting to delete a wrong id",HttpStatus.BAD_REQUEST);
	}
	
	@ExceptionHandler(EmptyResultDataAccessException.class)
	public ResponseEntity<Object> handleNosuchIdException(EmptyResultDataAccessException emptyResultDataAccessException) {
		return new ResponseEntity<Object>("No such id exist",HttpStatus.NOT_FOUND);
	}
	
	@ExceptionHandler(IdNotFoundException.class)
	public ResponseEntity<Object> handleNosuchIdException(IdNotFoundException emptyResultDataAccessException) {
		return new ResponseEntity<Object>("No such id found",HttpStatus.NOT_FOUND);
	}

	
	@ExceptionHandler(NoSuchElementException.class)
	public ResponseEntity<Object> handleNosuchElement(NoSuchElementException noSuchElementException) {
		return new ResponseEntity<Object>("No such value found",HttpStatus.NOT_FOUND);
	}
	
	@Override
	protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex,
			HttpHeaders headers, HttpStatus status, WebRequest request) {
        Map<String, String> errorMap = new HashMap<>();
        Map<String,String> map = new HashMap<>();
//        String string = "";
        ex.getBindingResult().getFieldErrors().forEach(error -> {
//        	str+=error.getField()+","+error.getDefaultMessage();
            errorMap.put(error.getField(), error.getDefaultMessage());
        });
        map.put("code", HttpStatus.BAD_REQUEST.toString());
        map.put("message", errorMap.toString());
        return new ResponseEntity<>(map, HttpStatus.BAD_REQUEST);   
	}
	
	@Override
	protected ResponseEntity<Object> handleHttpRequestMethodNotSupported(HttpRequestMethodNotSupportedException ex,
			HttpHeaders headers, HttpStatus status, WebRequest request) {
		// TODO Auto-generated method stub
		return new ResponseEntity<Object>("Change the request type",HttpStatus.BAD_REQUEST);
	}
	
   
	
}
