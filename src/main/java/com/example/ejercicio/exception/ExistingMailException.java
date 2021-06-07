package com.example.ejercicio.exception;

public class ExistingMailException extends Exception {
	private static final long serialVersionUID = 1L;
	
	public ExistingMailException(String message) {
	    super(message);
	  }

}
