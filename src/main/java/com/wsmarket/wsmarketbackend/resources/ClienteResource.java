package com.wsmarket.wsmarketbackend.resources;

import java.net.URI;

import javax.validation.Valid;

import com.wsmarket.wsmarketbackend.domains.Cliente;
import com.wsmarket.wsmarketbackend.dtos.ClienteDTO;
import com.wsmarket.wsmarketbackend.dtos.ClienteNewDTO;
import com.wsmarket.wsmarketbackend.mappers.ClienteMapper;
import com.wsmarket.wsmarketbackend.services.ClienteService;

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
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

@RestController
@RequestMapping(path = "/clientes")
public class ClienteResource {
	@Autowired
	private ClienteService clienteService;

	@Autowired
	private ClienteMapper clienteMapper;

	@GetMapping(path = "")
	public ResponseEntity<Page<ClienteDTO>> findAll(Pageable pageable) {
		Page<ClienteDTO> clientes = this.clienteService.findAll(pageable);
		return ResponseEntity.ok(clientes);
	}
	
	@GetMapping(path = "/pagination")
	public ResponseEntity<Page<ClienteDTO>> findPage(
		@RequestParam(name = "page", defaultValue = "0") Integer page,
		@RequestParam(name = "limit", defaultValue = "24") Integer linesPerPage,
		@RequestParam(name = "orderBy", defaultValue = "nome") String orderBy,
		@RequestParam(name = "direction", defaultValue = "ASC") String direction
	) {
		Page<ClienteDTO> clientes = this.clienteService.findPage(
			page,
			linesPerPage,
			orderBy,
			direction
		);

		return ResponseEntity.ok(clientes);
	}

	@GetMapping(path = "/{id}")
	public ResponseEntity<Cliente> findById(
		@PathVariable Long id
	) {
		Cliente cliente = this.clienteService.findById(id);

		return ResponseEntity.ok(cliente);
	}

	@PostMapping(path = "")
	public ResponseEntity<Void> create(
		@Valid @RequestBody ClienteNewDTO clienteNewDto
	) {
		ClienteDTO cliente = this.clienteService
			.create(this.clienteMapper.mapToCliente(clienteNewDto));

		URI uri = ServletUriComponentsBuilder
			.fromCurrentRequest()
			.path("/{id}")
			.buildAndExpand(cliente.getId())
			.toUri();

		return ResponseEntity.created(uri).build();
	}

	@PutMapping(path = "/{id}")
	public ResponseEntity<Void> update(
		@PathVariable Long id,
		@Valid @RequestBody ClienteDTO clienteDto
	) {
		clienteDto.setId(id);
		this.clienteService.update(id, clienteMapper.mapToCliente(clienteDto));

		return ResponseEntity.noContent().build();
	}

	@DeleteMapping(path = "/{id}")
	public ResponseEntity<Void> delete(
		@PathVariable Long id
	) {
		this.clienteService.delete(id);

		return ResponseEntity.noContent().build();
	}
}
