package com.wsmarket.wsmarketbackend.services;

import java.util.Calendar;
import java.util.Date;

import org.springframework.stereotype.Service;

import com.wsmarket.wsmarketbackend.domains.PagamentoComBoleto;

@Service
public class BoletoService {
	public static void hydratePagamento(PagamentoComBoleto pagamento, Date dataDoPedido) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(dataDoPedido);
		calendar.add(calendar.DAY_OF_MONTH, 7);
		
		pagamento.setDataPagamento(null);
		pagamento.setDataVencimento(calendar.getTime());
	}
}
