package com.capgemini.advertisement.exception;
public class AdvertisementException extends Exception {
	private static final long serialVersionUID = 1L;
	
	private String message;

	public AdvertisementException() {
		
	}

	public AdvertisementException(String message) {
		super(message);
		this.message=message;
	}
	
	public AdvertisementException(String message,Exception e) {
		super(message,e);
		this.message=message;
	}

	@Override
	public String toString() {
		return "ProductException [message=" + message + "]";
	}

	public String getMessage() {
		return message;
	}
	
	
}
