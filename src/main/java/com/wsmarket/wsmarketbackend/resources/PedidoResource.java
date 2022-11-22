package com.wsmarket.wsmarketbackend.resources;

import com.wsmarket.wsmarketbackend.domains.Pedido;
import com.wsmarket.wsmarketbackend.services.PedidoService;

import java.net.URI;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

@RestController
@RequestMapping(path = "/pedidos")
public class PedidoResource {
	@Autowired
	private PedidoService pedidoService;

	// @Autowired
	// private PedidoMapper pedidoMapper;

	@GetMapping(path = "/{id}")
	public ResponseEntity<Pedido> findById(
		@PathVariable Long id
	) {
		Pedido pedido = this.pedidoService.findById(id);

		return ResponseEntity.ok(pedido);
	}

	@PostMapping(path = "/")
	public ResponseEntity<Void> create(
		@Valid @RequestBody Pedido pedido
	) {
		Pedido newPedido = this.pedidoService
			.create(pedido);
			// .create(this.pedidoMapper.mapToPedido(pedido));

		URI uri = ServletUriComponentsBuilder
			.fromCurrentRequest()
			.path("/{id}")
			.buildAndExpand(newPedido.getId())
			.toUri();

		return ResponseEntity.created(uri).build();
	}
}
