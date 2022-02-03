package com.wsmarket.wsmarketbackend.domain;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import com.wsmarket.wsmarketbackend.domain.enums.EstadoPagamento;

@Entity
@Table(name = "tb_pagamento_com_boleto")
public class PagamentoComBoleto extends Pagamento {
	private static final long serialVersionUID = 1L;
	
	@Column(name = "data_pagamento")
	private Date dataPagamento;
	
	@Column(name = "data_vencimento")
	private Date dataVencimento;

	public PagamentoComBoleto() {

	}

	public PagamentoComBoleto(Long id, EstadoPagamento estado, Pedido pedido, Date dataPagamento, Date dataVencimento) {
		super(id, estado, pedido);
		this.dataPagamento = dataPagamento;
		this.dataVencimento = dataVencimento;
	}

	public Date getDataPagamento() {
		return dataPagamento;
	}

	public void setDataPagamento(Date dataPagamento) {
		this.dataPagamento = dataPagamento;
	}

	public Date getDataVencimento() {
		return dataVencimento;
	}

	public void setDataVencimento(Date dataVencimento) {
		this.dataVencimento = dataVencimento;
	}
}
