package com.wsmarket.wsmarketbackend.resources.exceptions;

import java.time.Instant;
import java.util.ArrayList;
import java.util.List;

public class ValidationError extends StandardError {
	private List<FieldMessage> errors = new ArrayList<>();
	
	public ValidationError(
		Integer status,
		String message,
		Instant timeStamp
	) {
		super(status, message, timeStamp);
	}

	public List<FieldMessage> getErrors() {
		return errors;
	}

	public void addError(String fieldName, String message) {
		errors.add(new FieldMessage(fieldName, message));
	}
}
