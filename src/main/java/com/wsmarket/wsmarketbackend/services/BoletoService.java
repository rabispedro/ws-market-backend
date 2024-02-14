package com.wsmarket.wsmarketbackend.services;

import java.util.Calendar;
import java.util.Date;

import com.wsmarket.wsmarketbackend.domains.PagamentoComBoleto;
import com.wsmarket.wsmarketbackend.services.interfaces.IBoletoService;

public class BoletoService extends BaseService implements IBoletoService {
	private BoletoService() {}

	public void hydratePagamento(PagamentoComBoleto pagamento, Date dataDoPedido) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(dataDoPedido);
		calendar.add(Calendar.DAY_OF_MONTH, 7);
		
		pagamento.setDataPagamento(null);
		pagamento.setDataVencimento(calendar.getTime());
	}
}
