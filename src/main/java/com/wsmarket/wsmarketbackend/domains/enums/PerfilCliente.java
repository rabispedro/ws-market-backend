package com.wsmarket.wsmarketbackend.domains.enums;

public enum PerfilCliente {
	ADMIN(1, "ROLE_ADMIN"),
	CLIENTE(2, "ROLE_CLIENTE");

	private Integer codigo;
	private String descricao;
	
	private PerfilCliente(Integer codigo, String descricao) {
		this.codigo = codigo;
		this.descricao = descricao;
	}

	public Integer getCodigo() {
		return codigo;
	}

	public String getDescricao() {
		return descricao;
	}

	public static PerfilCliente toEnum(Integer codigo) {
		if(codigo == null) {
			return null;
		}

		for(PerfilCliente perfil : PerfilCliente.values()) {
			if(codigo.equals(perfil.getCodigo())) {
				return perfil;
			}
		}

		throw new IllegalArgumentException("Id inválido: " + codigo);
	}
}
