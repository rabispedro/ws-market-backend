package com.wsmarket.wsmarketbackend.domains.enums;

public enum TipoCliente {
	PESSOA_FISICA(1, "Pessoa Fisica"),
	PESSOA_JURIDICA(2, "Pessoa Juridica");

	private Integer codigo;
	private String descricao;

	private TipoCliente(Integer codigo, String descricao) {
		this.codigo = codigo;
		this.descricao = descricao;
	}

	public Integer getCodigo() {
		return this.codigo;
	}

	public String getDescricao() {
		return this.descricao;
	}

	public static TipoCliente toEnum(Integer codigo) {
		if(codigo == null) {
			return null;
		}

		for(TipoCliente cliente : TipoCliente.values()) {
			if(codigo.equals(cliente.getCodigo())) {
				return cliente;
			}
		}

		throw new IllegalArgumentException("Codigo invalido: " + codigo);
	}
}
