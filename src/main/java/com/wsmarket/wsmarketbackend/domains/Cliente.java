package com.wsmarket.wsmarketbackend.domains;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import javax.persistence.CascadeType;
import javax.persistence.CollectionTable;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.wsmarket.wsmarketbackend.domains.enums.PerfilCliente;
import com.wsmarket.wsmarketbackend.domains.enums.TipoCliente;

@Table(name = "tb_cliente")
public class Cliente extends BaseDomain {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private Long id;

	@Column(name = "nome")
	private String nome;

	@Column(name = "email", unique = true)
	private String email;

	@Column(name = "cpf_ou_cnpj")
	private String cpfOuCnpj;

	@Column(name = "tipo")
	private Integer tipo;

	@JsonIgnore
	@Column(name = "senha")
	private String senha;

	@OneToMany(mappedBy = "cliente", cascade = CascadeType.ALL)
	@Column(name = "enderecos")
	private List<Endereco> enderecos = new ArrayList<>();

	@ElementCollection
	@CollectionTable(name = "tb_telefone")
	@Column(name = "telefones")
	private Set<String> telefones = new HashSet<>();

	@ElementCollection(fetch = FetchType.EAGER)
	@CollectionTable(name = "tb_cliente_perfil")
	@Column(name = "perfil")
	private Set<Integer> perfis = new HashSet<>();

	@JsonIgnore
	@OneToMany(mappedBy = "cliente")
	@Column(name = "pedidos")
	private List<Pedido> pedidos = new ArrayList<>();

	public Cliente() {
		this.addPerfil(PerfilCliente.CLIENTE);
	}

	public Cliente(
		Long id,
		String nome,
		String email, 
		String cpfOuCnpj,
		TipoCliente tipo,
		String senha
	) {
		this.id = id;
		this.nome = nome;
		this.email = email;
		this.cpfOuCnpj = cpfOuCnpj;
		this.tipo = (tipo == null) ? null : tipo.getCodigo();
		this.senha = senha;
		this.addPerfil(PerfilCliente.CLIENTE);
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

	public TipoCliente getTipo() {
		return TipoCliente.toEnum(tipo);
	}

	public void setTipo(TipoCliente tipo) {
		this.tipo = tipo.getCodigo();
	}

	public String getSenha() {
		return this.senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}

	public List<Endereco> getEnderecos() {
		return enderecos;
	}

	public void setEnderecos(List<Endereco> enderecos) {
		this.enderecos = enderecos;
	}

	public Set<String> getTelefones() {
		return telefones;
	}

	public void setTelefones(Set<String> telefones) {
		this.telefones = telefones;
	}

	public Set<PerfilCliente> getPerfis() {
		return this.perfis
			.stream()
			.map(PerfilCliente::toEnum)
			.collect(Collectors.toSet());
	}

	public void addPerfil(PerfilCliente perfil) {
		this.perfis.add(perfil.getCodigo());
	}

	public List<Pedido> getPedidos() {
		return pedidos;
	}

	public void setPedidos(List<Pedido> pedidos) {
		this.pedidos = pedidos;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Cliente other = (Cliente) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}
}
