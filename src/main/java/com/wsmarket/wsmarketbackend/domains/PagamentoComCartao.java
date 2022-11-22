package com.wsmarket.wsmarketbackend.domains;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonTypeName;
import com.wsmarket.wsmarketbackend.domains.enums.EstadoPagamento;

@Entity
@Table(name = "tb_pagamento_com_cartao")
@JsonTypeName("pagamentoComCartao")
public class PagamentoComCartao extends Pagamento {
	private static final long serialVersionUID = 1L;

	@Column(name = "numero_parcelas")
	private Integer numeroParcelas;

	public PagamentoComCartao() {

	}

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
}
