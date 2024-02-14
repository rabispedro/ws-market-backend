package com.wsmarket.wsmarketbackend.dtos;

import java.io.Serializable;
import java.util.Set;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;

import org.hibernate.validator.constraints.Length;

import com.wsmarket.wsmarketbackend.services.validations.ClienteCreate;

@ClienteCreate
public record ClienteNewDto(
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
	String email,

	@NotEmpty(message = "This field is required")
	String cpfOuCnpj,

	Integer tipo,

	@NotEmpty(message = "This field is required")
	String senha,

	@NotEmpty(message = "This field is required")
	String logradouro,

	@NotEmpty(message = "This field is required")
	String numero,

	String complemento,

	String bairro,

	@NotEmpty(message = "This field is required")
	String cep,

	@NotEmpty(message = "This field is required")
	Set<String> telefones,

	Long cidadeId
) implements Serializable {
	private static final long serialVersionUID = 1L;
}
