package com.wsmarket.wsmarketbackend.services;

import java.util.Optional;

import com.wsmarket.wsmarketbackend.domain.Pedido;
import com.wsmarket.wsmarketbackend.repositories.PedidoRepository;
import com.wsmarket.wsmarketbackend.services.exceptions.ObjectNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PedidoService {
	@Autowired
	private PedidoRepository pedidoRepository;

	public Pedido findById(Long id) {
		Optional<Pedido> pedido = this.pedidoRepository.findById(id);

		return pedido.orElseThrow(() -> (
			new ObjectNotFoundException(
				"Objeto não encontrado! " +
				"Id: " + id + ", " +
				"Tipo: " + Pedido.class.getName() + "."
			)
		));
	}
}
