package com.wsmarket.wsmarketbackend.services.exceptions;

public class DataIntegrityException extends RuntimeException {
	private static final Long serialVersionUID = 1L;

	public DataIntegrityException(String message) {
		super(message);
	}

	public DataIntegrityException(String message, Throwable cause) {
		super(message, cause);
	}
}
