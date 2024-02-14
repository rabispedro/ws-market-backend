package com.wsmarket.wsmarketbackend.domains;

import javax.persistence.Column;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonTypeName;
import com.wsmarket.wsmarketbackend.domains.enums.EstadoPagamento;

@Table(name = "tb_pagamento_com_cartao")
@JsonTypeName("pagamentoComCartao")
public class PagamentoComCartao extends Pagamento {
	@Column(name = "numero_parcelas")
	private Integer numeroParcelas;

	public PagamentoComCartao() {}

	public PagamentoComCartao(
		Long id,
		EstadoPagamento estado,
		Pedido pedido,
		Integer numeroParcelas
	) {
		super(id, estado, pedido);
		this.numeroParcelas = numeroParcelas;
	}

	public Integer getNumeroParcelas() {
		return numeroParcelas;
	}

	public void setNumeroParcelas(Integer numeroParcelas) {
		this.numeroParcelas = numeroParcelas;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + ((numeroParcelas == null) ? 0 : numeroParcelas.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (!super.equals(obj))
			return false;
		if (getClass() != obj.getClass())
			return false;
		PagamentoComCartao other = (PagamentoComCartao) obj;
		if (numeroParcelas == null) {
			if (other.numeroParcelas != null)
				return false;
		} else if (!numeroParcelas.equals(other.numeroParcelas))
			return false;
		return true;
	}
}
