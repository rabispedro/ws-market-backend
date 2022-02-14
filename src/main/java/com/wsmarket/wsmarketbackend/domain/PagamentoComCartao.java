package com.wsmarket.wsmarketbackend.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import com.wsmarket.wsmarketbackend.domain.enums.EstadoPagamento;

@Entity
@Table(name = "tb_pagamento_com_cartao")
public class PagamentoComCartao extends Pagamento {
	private static final long serialVersionUID = 1L;

	@Column(name = "numero_parcelas")
	private Integer numeroParcelas;

	public PagamentoComCartao() {

	}

	public PagamentoComCartao(Long id, EstadoPagamento estado, Pedido pedido, Integer numeroParcelas) {
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
