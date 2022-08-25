package com.bluestone.blueStoneCodeTest.exception;

public class RegistrationFailException extends RuntimeException {
	private static final long serialVersionUID = 1L;

	public RegistrationFailException() {
		super();
	}

	public RegistrationFailException(String message) {
		super(message);
	}

	public RegistrationFailException(String message, Throwable cause) {
		super(message, cause);
	}
}