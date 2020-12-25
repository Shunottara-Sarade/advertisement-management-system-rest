package com.capgemini.advertisement.exception;
public class StaffException extends Exception
{
	private static final long serialVersionUID = 1L;
	private String message;

	public StaffException() {
		
	}

	public StaffException(String message) {
		super(message);
		this.message=message;
	}
	
	public StaffException(String message,Exception e) {
		super(message,e);
		this.message=message;
	}

	@Override
	public String toString() {
		return "StaffException [message=" + message + "]";
	}

	public String getMessage() {
		return message;
	}
}
