package com.wsmarket.wsmarketbackend.dtos;

import java.io.Serializable;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;

import org.hibernate.validator.constraints.Length;

import com.wsmarket.wsmarketbackend.services.validations.ClienteUpdate;

@ClienteUpdate
public record ClienteDto(
	Long id,
	
	@NotEmpty(message = "This field is required")
	@Length(
		min = 3,
		max = 80,
		message = "This field must contain between 3 and 80 characters"
	)
	String nome,
	
	@NotEmpty(message = "This field is required")
	@Email(message = "Invalid email")
	String email
) implements Serializable {
	private static final long serialVersionUID = 1L;
}
