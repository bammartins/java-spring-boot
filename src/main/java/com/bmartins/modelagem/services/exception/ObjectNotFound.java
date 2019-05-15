package com.bmartins.modelagem.services.exception;

public class ObjectNotFound extends RuntimeException{
	private static final long serialVersionUID = 1L;
	
	public ObjectNotFound(String message) {
		super(message);
	}
	
	public ObjectNotFound(String message, Throwable cause) {
		super(message, cause);
	}
	
	
}
