package com.wsmarket.wsmarketbackend.services;

import com.wsmarket.wsmarketbackend.domains.Cliente;
import com.wsmarket.wsmarketbackend.dtos.ClienteDTO;
import com.wsmarket.wsmarketbackend.mappers.ClienteMapper;
import com.wsmarket.wsmarketbackend.repositories.ClienteRepository;
import com.wsmarket.wsmarketbackend.repositories.EnderecoRepository;
import com.wsmarket.wsmarketbackend.services.exceptions.DataIntegrityException;
import com.wsmarket.wsmarketbackend.services.exceptions.ObjectNotFoundException;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class ClienteService {
	@Autowired
	private ClienteRepository clienteRepository;

	@Autowired
	private EnderecoRepository enderecoRepository;

	@Autowired
	private ClienteMapper clienteMapper;

	public Page<ClienteDTO> findAll(Pageable pageable) {
		Page<Cliente> clientes = this.clienteRepository.findAll(pageable);
		return clientes.map(cliente -> clienteMapper.mapToClienteDTO(cliente));
	}

	public Page<ClienteDTO> findPage(
		Integer page,
		Integer linesPerPage,
		String orderBy,
		String direction
	) {
		PageRequest pageRequest = PageRequest
			.of(page, linesPerPage, Direction.valueOf(direction), orderBy)
		;

		Page<Cliente> clientes = this.clienteRepository.findAll(pageRequest);
		return clientes.map(cliente -> clienteMapper.mapToClienteDTO(cliente));
	}

	public Cliente findById(Long id) {
		Cliente cliente = this.clienteRepository.findById(id)
			.orElseThrow(() -> (
				new ObjectNotFoundException(
					"Objeto não encontrado! " +
					"Id: " + id + ", " +
					"Tipo: " + Cliente.class.getName() + "."
				)
			)
		);

		return cliente;
	}

	@Transactional
		public ClienteDTO create(Cliente cliente) {
			cliente.setId(null);
			cliente = this.clienteRepository.save(cliente);
			this.enderecoRepository.saveAll(cliente.getEnderecos());
			return this.clienteMapper.mapToClienteDTO(cliente);
		}

	public ClienteDTO update(Long id, Cliente cliente) {
		cliente.setId(null);
		Cliente newCliente = this.findById(id);
		this.clienteMapper.mapToNewCliente(newCliente, cliente);
		Cliente updatedCliente = this.clienteRepository.save(newCliente);

		return clienteMapper.mapToClienteDTO(updatedCliente);
	}

	public void delete(Long id) {
		this.findById(id);

		try {
			this.clienteRepository.deleteById(id);
		} catch (DataIntegrityViolationException exception) {
			throw new DataIntegrityException(
				"It's not possible to delete a 'Cliente' with relations."
			);
		}

		return;
	}

	
}
