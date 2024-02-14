package com.wsmarket.wsmarketbackend.resources;

import java.net.URI;

import javax.validation.Valid;

import com.wsmarket.wsmarketbackend.domains.Cliente;
import com.wsmarket.wsmarketbackend.dtos.ClienteDto;
import com.wsmarket.wsmarketbackend.dtos.ClienteNewDto;
import com.wsmarket.wsmarketbackend.mappers.interfaces.IClienteMapper;
import com.wsmarket.wsmarketbackend.services.interfaces.IClienteService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

@RequestMapping(path = "/clientes")
public class ClienteResource extends BaseResource {
	private final IClienteService _clienteService;
	private final IClienteMapper _clienteMapper;

	public ClienteResource(
		@Autowired IClienteService clienteService,
		@Autowired IClienteMapper clienteMapper
	) {
		_clienteService = clienteService;
		_clienteMapper = clienteMapper;
	}

	@GetMapping(path = "")
	public ResponseEntity<Page<ClienteDto>> findAll(Pageable pageable) {
		return ResponseEntity.ok(_clienteService.findAll(pageable));
	}
	
	@GetMapping(path = "/pagination")
	public ResponseEntity<Page<ClienteDto>> findPage(
		@RequestParam(name = "page", defaultValue = "0") Integer page,
		@RequestParam(name = "limit", defaultValue = "24") Integer linesPerPage,
		@RequestParam(name = "orderBy", defaultValue = "nome") String orderBy,
		@RequestParam(name = "direction", defaultValue = "ASC") String direction
	) {
		return ResponseEntity.ok(_clienteService.findPage(page, linesPerPage, orderBy, direction));
	}

	@GetMapping(path = "/{id}")
	public ResponseEntity<Cliente> findById(@PathVariable Long id) {
		return ResponseEntity.ok(_clienteService.findById(id));
	}

	@PostMapping(path = "")
	public ResponseEntity<Void> create(@Valid @RequestBody ClienteNewDto clienteNewDto) {
		ClienteDto cliente = _clienteService.create(_clienteMapper.mapToCliente(clienteNewDto));

		URI uri = ServletUriComponentsBuilder
			.fromCurrentRequest()
			.path("/{id}")
			.buildAndExpand(cliente.id())
			.toUri();

		return ResponseEntity.created(uri).build();
	}

	@PutMapping(path = "/{id}")
	public ResponseEntity<Void> update(
		@PathVariable Long id,
		@Valid @RequestBody ClienteDto clienteDto
	) {
		_clienteService.update(id, _clienteMapper.mapToCliente(clienteDto));
		return ResponseEntity.noContent().build();
	}

	@DeleteMapping(path = "/{id}")
	public ResponseEntity<Void> delete(@PathVariable Long id) {
		_clienteService.delete(id);
		return ResponseEntity.noContent().build();
	}
}
