package com.wsmarket.wsmarketbackend.services;

import com.wsmarket.wsmarketbackend.domains.Cliente;
import com.wsmarket.wsmarketbackend.dtos.ClienteDto;
import com.wsmarket.wsmarketbackend.mappers.ClienteMapper;
import com.wsmarket.wsmarketbackend.mappers.interfaces.IClienteMapper;
import com.wsmarket.wsmarketbackend.repositories.ClienteRepository;
import com.wsmarket.wsmarketbackend.repositories.EnderecoRepository;
import com.wsmarket.wsmarketbackend.services.exceptions.DataIntegrityException;
import com.wsmarket.wsmarketbackend.services.exceptions.ObjectNotFoundException;
import com.wsmarket.wsmarketbackend.services.interfaces.IClienteService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.transaction.annotation.Transactional;

public class ClienteService extends BaseService implements IClienteService {
	private final ClienteRepository _clienteRepository;
	private final EnderecoRepository _enderecoRepository;
	private final IClienteMapper _clienteMapper;

	public ClienteService(
		@Autowired ClienteRepository clienteRepository,
		@Autowired EnderecoRepository enderecoRepository,
		@Autowired ClienteMapper clienteMapper
	) {
		_clienteRepository = clienteRepository;
		_enderecoRepository = enderecoRepository;
		_clienteMapper = clienteMapper;
	}

	public Page<ClienteDto> findAll(Pageable pageable) {
		Page<Cliente> clientes = _clienteRepository.findAll(pageable);

		return clientes.map(_clienteMapper::mapToClienteDto);
	}

	public Page<ClienteDto> findPage(Integer page, Integer linesPerPage, String orderBy, String direction) {
		PageRequest pageRequest = PageRequest
			.of(page, linesPerPage, Direction.valueOf(direction), orderBy);

		Page<Cliente> clientes = _clienteRepository.findAll(pageRequest);

		return clientes.map(_clienteMapper::mapToClienteDto);
	}

	public Cliente findById(Long id) {
		return _clienteRepository.findById(id)
			.orElseThrow(() -> (
				new ObjectNotFoundException(
					"Objeto não encontrado! " +
					"Id: " + id + ", " +
					"Tipo: " + Cliente.class.getName() + ".")));
	}

	@Transactional
	public ClienteDto create(Cliente cliente) {
		cliente.setId(null);
		cliente = _clienteRepository.save(cliente);
		_enderecoRepository.saveAll(cliente.getEnderecos());

		return _clienteMapper.mapToClienteDto(cliente);
	}

	public ClienteDto update(Long id, Cliente cliente) {
		cliente.setId(null);
		Cliente newCliente = this.findById(id);
		_clienteMapper.mapToNewCliente(newCliente, cliente);
		Cliente updatedCliente = _clienteRepository.save(newCliente);

		return _clienteMapper.mapToClienteDto(updatedCliente);
	}

	public void delete(Long id) {
		this.findById(id);

		try {
			_clienteRepository.deleteById(id);
		} catch (DataIntegrityViolationException exception) {
			throw new DataIntegrityException(
				"It's not possible to delete a 'Cliente' with relations."
			);
		}
	}
}
