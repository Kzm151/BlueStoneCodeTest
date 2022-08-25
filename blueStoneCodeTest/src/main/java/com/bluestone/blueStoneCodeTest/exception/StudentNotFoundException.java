package com.bluestone.blueStoneCodeTest.exception;

public class StudentNotFoundException extends RuntimeException {
	private static final long serialVersionUID = 1L;

	public StudentNotFoundException() {
		super();
	}

	public StudentNotFoundException(String message) {
		super(message);
	}

	public StudentNotFoundException(String message, Throwable cause) {
		super(message, cause);
	}
}