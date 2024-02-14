package com.wsmarket.wsmarketbackend.services.interfaces;

import com.wsmarket.wsmarketbackend.domains.Pedido;

public interface IPedidoService {
	Pedido findById(Long id);
	Pedido create(Pedido pedido);
}
