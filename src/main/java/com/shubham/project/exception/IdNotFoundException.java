package com.shubham.project.exception;

public class IdNotFoundException extends RuntimeException{

	private static final long serialVersionUID = -5915835068966702266L;
	
	public IdNotFoundException(String msg) {
		super(msg);
	}

}
