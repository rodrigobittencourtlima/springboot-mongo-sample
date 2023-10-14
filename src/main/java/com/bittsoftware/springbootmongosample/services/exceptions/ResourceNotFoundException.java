package com.bittsoftware.springbootmongosample.services.exceptions;

public class ResourceNotFoundException extends RuntimeException {

	private static final long serialVersionUID = -2005716954242112873L;

	public ResourceNotFoundException(String message) {
		super(message);
	}

}
