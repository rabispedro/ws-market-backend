package com.wsmarket.wsmarketbackend.resources;

import com.wsmarket.wsmarketbackend.domains.Pedido;
import com.wsmarket.wsmarketbackend.services.PedidoService;
import com.wsmarket.wsmarketbackend.services.interfaces.IPedidoService;

import java.net.URI;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

@RequestMapping(path = "/pedidos")
public class PedidoResource extends BaseResource {
	private final IPedidoService _pedidoService;

	public PedidoResource(
		@Autowired IPedidoService pedidoService
	) {
		_pedidoService = pedidoService;
	}

	@GetMapping(path = "/{id}")
	public ResponseEntity<Pedido> findById(@PathVariable Long id) {
		return ResponseEntity.ok(_pedidoService.findById(id));
	}

	@PostMapping(path = "/")
	public ResponseEntity<Void> create(@Valid @RequestBody Pedido pedido) {
		Pedido newPedido = _pedidoService.create(pedido);

		URI uri = ServletUriComponentsBuilder
			.fromCurrentRequest()
			.path("/{id}")
			.buildAndExpand(newPedido.getId())
			.toUri();

		return ResponseEntity.created(uri).build();
	}
}
