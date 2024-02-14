package com.wsmarket.wsmarketbackend.dtos;

import java.io.Serializable;

public record CredenciaisDto(
	String email,
	
	String senha
) implements Serializable {
	private static final long serialVersionUID = 1L;
}
