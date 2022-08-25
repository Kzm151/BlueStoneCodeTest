package com.bluestone.blueStoneCodeTest.exception;

import java.util.Date;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class StudentExceptionController extends ResponseEntityExceptionHandler {

	/*
	 * to handle StudentNotFoundException with ErrorDetails format
	 */
	@ExceptionHandler(StudentNotFoundException.class)
	public ResponseEntity<ErrorDetails> handleStudentNotFoundException(StudentNotFoundException e, WebRequest req) {
		ErrorDetails errorDetail = new ErrorDetails(new Date(), e.getMessage(), req.getDescription(false));
		return new ResponseEntity<ErrorDetails>(errorDetail, HttpStatus.NOT_FOUND);

	}

	/*
	 * to handle RegistrationFailException with ErrorDetails format
	 */
	@ExceptionHandler(RegistrationFailException.class)
	public ResponseEntity<ErrorDetails> handleRegistrationFailException(RegistrationFailException e, WebRequest req) {
		ErrorDetails errorDetail = new ErrorDetails(new Date(), e.getMessage(), req.getDescription(false));
		return new ResponseEntity<ErrorDetails>(errorDetail, HttpStatus.BAD_REQUEST);

	}
}
