package com.wsmarket.wsmarketbackend.resources;

import com.wsmarket.wsmarketbackend.domains.Pedido;
import com.wsmarket.wsmarketbackend.services.PedidoService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "/pedidos")
public class PedidoResource {
	@Autowired
	private PedidoService pedidoService;

	@GetMapping(path = "/{id}")
	public ResponseEntity<Pedido> findById(
		@PathVariable Long id
	) {
		Pedido pedido = this.pedidoService.findById(id);

		return ResponseEntity.ok(pedido);
	}
}
