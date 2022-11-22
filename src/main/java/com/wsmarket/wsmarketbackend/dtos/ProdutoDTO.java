package com.wsmarket.wsmarketbackend.dtos;

import java.io.Serializable;

import javax.validation.constraints.NotEmpty;

import org.hibernate.validator.constraints.Length;

public class ProdutoDTO implements Serializable {
	private static final long serialVersionUID = 1L;

	public Long id;

	@NotEmpty(message = "This field is required")
	@Length(
		min = 3,
		max = 80,
		message = "This field must contain between 3 and 80 characters"
	)
	public String nome;

	@NotEmpty(message = "This field is required")
	Double preco;

	public ProdutoDTO() {
	}

	public ProdutoDTO(
		Long id,
		String nome,
		Double preco
	) {
		this.id = id;
		this.nome = nome;
		this.preco = preco;
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

	public Double getPreco() {
		return preco;
	}

	public void setPreco(Double preco) {
		this.preco = preco;
	}
}