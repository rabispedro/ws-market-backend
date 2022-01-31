package com.wsmarket.wsmarketbackend.domain.enums;

public enum TipoCliente {
	PESSOA_FISICA(1, "Pessoa Fisica"),
	PESSOA_JURIDICA(2, "Pessoa Juridica");

	private int codigo;
	private String descricao;

	private TipoCliente(int codigo, String descricao) {
		this.codigo = codigo;
		this.descricao = descricao;
	}

	public int getCodigo() {
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
