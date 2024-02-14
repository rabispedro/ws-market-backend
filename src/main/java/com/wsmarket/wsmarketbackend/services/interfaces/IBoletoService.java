package com.wsmarket.wsmarketbackend.services.interfaces;

import java.util.Date;

import com.wsmarket.wsmarketbackend.domains.PagamentoComBoleto;

public interface IBoletoService {
	void hydratePagamento(PagamentoComBoleto pagamento, Date dataDoPedido);
}
