package com.wsmarket.wsmarketbackend.resources.exceptions;

import java.time.Instant;

import javax.servlet.http.HttpServletRequest;

import com.wsmarket.wsmarketbackend.services.exceptions.DataIntegrityException;
import com.wsmarket.wsmarketbackend.services.exceptions.ObjectNotFoundException;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class ResourceExceptionHandler {

	@ExceptionHandler(ObjectNotFoundException.class)
	public ResponseEntity<StandardError> objectNotFound(
		ObjectNotFoundException error,
		HttpServletRequest request
	) {
		StandardError objectNotFoundError = new StandardError(
			HttpStatus.NOT_FOUND.value(),
			error.getMessage(),
			Instant.now()
		);

		return ResponseEntity
			.status(HttpStatus.NOT_FOUND)
			.body(objectNotFoundError)
		;
	}

	@ExceptionHandler(DataIntegrityException.class)
	public ResponseEntity<StandardError> dataIntegrity(
		DataIntegrityException error,
		HttpServletRequest request
	) {
		StandardError dataIntegrityError = new StandardError(
			HttpStatus.BAD_REQUEST.value(),
			error.getMessage(),
			Instant.now()
		);

		return ResponseEntity
			.status(HttpStatus.BAD_REQUEST)
			.body(dataIntegrityError)
		;
	}

	@ExceptionHandler(MethodArgumentNotValidException.class)
	public ResponseEntity<StandardError> dataValidation(
		MethodArgumentNotValidException error,
		HttpServletRequest request
	) {
		ValidationError dataValidationError = new ValidationError(
			HttpStatus.BAD_REQUEST.value(),
			"Validation Error",
			Instant.now()
		);

		for(FieldError err : error.getBindingResult().getFieldErrors()) {
			dataValidationError.addError(err.getField(), err.getDefaultMessage());
		}

		return ResponseEntity
			.status(HttpStatus.BAD_REQUEST)
			.body(dataValidationError)
		;
	}
}
