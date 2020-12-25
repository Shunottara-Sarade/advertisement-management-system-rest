package com.capgemini.advertisement.exception;
public class CustomerException extends Exception {
	private static final long serialVersionUID = 1L;
	
	private String message;

	public CustomerException() {
		
	}

	public CustomerException(String message) {
		super(message);
		this.message=message;
	}
	
	public CustomerException(String message,Exception e) {
		super(message,e);
		this.message=message;
	}

	@Override
	public String toString() {
		return "CustomerException [message=" + message + "]";
	}

	public String getMessage() {
		return message;
	}
	
	
}