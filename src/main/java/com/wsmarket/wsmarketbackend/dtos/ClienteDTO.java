package com.wsmarket.wsmarketbackend.dtos;

import java.io.Serializable;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;

import org.hibernate.validator.constraints.Length;

import com.wsmarket.wsmarketbackend.services.validations.ClienteUpdate;

@ClienteUpdate
public class ClienteDTO implements Serializable {
	private static final long serialVersionUID = 1L;
	
	private Long id;

	@NotEmpty(message = "This field is required")
	@Length(
		min = 3,
		max = 80,
		message = "This field must contain between 3 and 80 characters"
	)
	private String nome;

	@NotEmpty(message = "This field is required")
	@Email(message = "Invalid email")
	private String email;

	public ClienteDTO() {
	}

	public ClienteDTO(Long id, String nome, String email) {
		this.id = id;
		this.nome = nome;
		this.email = email;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}
}
