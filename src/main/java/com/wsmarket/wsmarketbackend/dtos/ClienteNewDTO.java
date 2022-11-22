package com.wsmarket.wsmarketbackend.dtos;

import java.io.Serializable;
import java.util.Set;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;

import org.hibernate.validator.constraints.Length;

import com.wsmarket.wsmarketbackend.services.validations.ClienteCreate;

@ClienteCreate
public class ClienteNewDTO implements Serializable {
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
	
	@NotEmpty(message = "This field is required")
	private String cpfOuCnpj;

	private Integer tipo;
	
	@NotEmpty(message = "This field is required")
	private String logradouro;
	
	@NotEmpty(message = "This field is required")
	private String numero;
	
	private String complemento;
	
	private String bairro;
	
	@NotEmpty(message = "This field is required")
	private String cep;
	
	@NotEmpty(message = "This field is required")
	private Set<String> telefones;
	
	private Long cidadeId;
	
	public ClienteNewDTO() {
	}

	public ClienteNewDTO(
		Long id,
		String nome,
		String email,
		String cpfOuCnpj,
		Integer tipo,
		String logradouro,
		String numero,
		String complemento,
		String bairro,
		String cep,
		Long cidadeId
	) {
		this.id = id;
		this.nome = nome;
		this.email = email;
		this.cpfOuCnpj = cpfOuCnpj;
		this.tipo = tipo;
		this.logradouro = logradouro;
		this.numero = numero;
		this.complemento = complemento;
		this.bairro = bairro;
		this.cep = cep;
		this.cidadeId = cidadeId;
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

	public String getCpfOuCnpj() {
		return cpfOuCnpj;
	}

	public void setCpfOuCnpj(String cpfOuCnpj) {
		this.cpfOuCnpj = cpfOuCnpj;
	}

	public Integer getTipo() {
		return tipo;
	}

	public void setTipo(Integer tipo) {
		this.tipo = tipo;
	}

	public String getLogradouro() {
		return logradouro;
	}

	public void setLogradouro(String logradouro) {
		this.logradouro = logradouro;
	}

	public String getNumero() {
		return numero;
	}

	public void setNumero(String numero) {
		this.numero = numero;
	}

	public String getComplemento() {
		return complemento;
	}

	public void setComplemento(String complemento) {
		this.complemento = complemento;
	}

	public String getBairro() {
		return bairro;
	}

	public void setBairro(String bairro) {
		this.bairro = bairro;
	}

	public String getCep() {
		return cep;
	}

	public void setCep(String cep) {
		this.cep = cep;
	}

	public Set<String> getTelefones() {
		return telefones;
	}

	public void setTelefones(Set<String> telefones) {
		this.telefones = telefones;
	}

	public Long getCidadeId() {
		return cidadeId;
	}

	public void setCidadeId(Long cidadeId) {
		this.cidadeId = cidadeId;
	}
}
