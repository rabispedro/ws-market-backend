package com.wsmarket.wsmarketbackend.dtos;

import java.io.Serializable;

import javax.validation.constraints.NotEmpty;

import com.wsmarket.wsmarketbackend.domains.Categoria;

import org.hibernate.validator.constraints.Length;

public class CategoriaDTO implements Serializable {
	private static final long serialVersionUID = 1L;

	private Long id;

	@NotEmpty(message = "This field is required")
	@Length(min = 3, max = 80, message = "This field must contain between 3 and 80 characters")
	private String nome;

	public CategoriaDTO() {

	}

	public CategoriaDTO(Categoria categoria) {
		this.id = categoria.getId();
		this.nome = categoria.getNome();
	}

	public Categoria fromDTO() {
		return new Categoria(null, this.nome);
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
}
