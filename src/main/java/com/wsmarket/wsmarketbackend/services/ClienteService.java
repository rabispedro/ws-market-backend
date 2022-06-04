package com.wsmarket.wsmarketbackend.services;

import com.wsmarket.wsmarketbackend.domains.Cliente;
import com.wsmarket.wsmarketbackend.dtos.ClienteDTO;
import com.wsmarket.wsmarketbackend.repositories.ClienteRepository;
import com.wsmarket.wsmarketbackend.services.exceptions.DataIntegrityException;
import com.wsmarket.wsmarketbackend.services.exceptions.ObjectNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

@Service
public class ClienteService {
	@Autowired
	private ClienteRepository clienteRepository;

	public Page<ClienteDTO> findAll(Pageable pageable) {
		Page<Cliente> clientes = this.clienteRepository.findAll(pageable);
		return clientes.map(cliente -> new ClienteDTO(cliente));
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
		return clientes.map(cliente -> new ClienteDTO(cliente));
	}

	public Cliente findById(Long id) {
		Cliente cliente = this.clienteRepository.findById(id)
			.orElseThrow(() -> (
				new ObjectNotFoundException(
					"Objeto não encontrado! " +
					"Id: " + id + ", " +
					"Tipo: " + Cliente.class.getName() + "."
				)
			))
		;

		return cliente;
	}

	//	public ClienteDTO create(Cliente cliente) {}

	public ClienteDTO update(Cliente cliente) {
		Cliente newCliente = this.findById(cliente.getId());
		this.updateClienteData(newCliente, cliente);
		Cliente updatedCliente = this.clienteRepository.save(newCliente);

		return new ClienteDTO(updatedCliente);
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

	private void updateClienteData(
		Cliente updatedClient,
		Cliente formerClient
	) {
		updatedClient.setNome(formerClient.getNome());
		updatedClient.setEmail(formerClient.getEmail());
		return;
	}
}
