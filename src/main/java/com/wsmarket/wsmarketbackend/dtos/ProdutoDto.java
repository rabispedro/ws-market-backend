package com.wsmarket.wsmarketbackend.dtos;

import java.io.Serializable;

import javax.validation.constraints.NotEmpty;

import org.hibernate.validator.constraints.Length;

public record ProdutoDto(
	Long id,

	@NotEmpty(message = "This field is required")
	@Length(
		min = 3,
		max = 80,
		message = "This field must contain between 3 and 80 characters"
	)
	String nome,

	@NotEmpty(message = "This field is required")
	Double preco
) implements Serializable {
	private static final long serialVersionUID = 1L;
}