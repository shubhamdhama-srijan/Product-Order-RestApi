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
		Map<String, String> errorMap = new HashMap<>();
		errorMap.put("Error"," Attempting to delete a wrong id");
        Map<String,String> map = new HashMap<>();
		map.put("code", HttpStatus.BAD_REQUEST.toString());
        map.put("message", errorMap.toString());
		return new ResponseEntity<Object>(map,HttpStatus.BAD_REQUEST);
	}
	
	@ExceptionHandler(EmptyResultDataAccessException.class)
	public ResponseEntity<Object> handleNosuchIdException(EmptyResultDataAccessException emptyResultDataAccessException) {
		Map<String, String> errorMap = new HashMap<>();
		errorMap.put("Error"," No such id Exist");
        Map<String,String> map = new HashMap<>();
		map.put("code", HttpStatus.NOT_FOUND.toString());
        map.put("message", errorMap.toString());
		return new ResponseEntity<Object>(map,HttpStatus.NOT_FOUND);
	}
	
//	for getting orders by product id
	@ExceptionHandler(IdNotFoundException.class)
	public ResponseEntity<Object> handleNosuchIdException(IdNotFoundException emptyResultDataAccessException) {
		Map<String, String> errorMap = new HashMap<>();
		errorMap.put("Error"," No such id found");
        Map<String,String> map = new HashMap<>();
        map.put("code", HttpStatus.NOT_FOUND.toString());
        map.put("message", errorMap.toString());
		return new ResponseEntity<Object>(map,HttpStatus.NOT_FOUND);
	}

//	for getting product by id
	@ExceptionHandler(NoSuchElementException.class)
	public ResponseEntity<Object> handleNosuchElement(NoSuchElementException noSuchElementException) {
		Map<String, String> errorMap = new HashMap<>();
		errorMap.put("Error"," No such value found");
        Map<String,String> map = new HashMap<>();
        map.put("code", HttpStatus.NOT_FOUND.toString());
        map.put("message", errorMap.toString());
		return new ResponseEntity<Object>(map,HttpStatus.NOT_FOUND);
	}
	

//	for validation
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
		
		Map<String, String> errorMap = new HashMap<>();
		errorMap.put("Error"," Change the request type");
        Map<String,String> map = new HashMap<>();
		map.put("code", HttpStatus.BAD_REQUEST.toString());
        map.put("message", errorMap.toString());
		return new ResponseEntity<Object>(map,HttpStatus.BAD_REQUEST);
	}
	
   
	
}
