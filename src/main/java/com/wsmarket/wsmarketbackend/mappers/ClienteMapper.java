package com.wsmarket.wsmarketbackend.mappers;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.wsmarket.wsmarketbackend.domains.Cidade;
import com.wsmarket.wsmarketbackend.domains.Cliente;
import com.wsmarket.wsmarketbackend.domains.Endereco;
import com.wsmarket.wsmarketbackend.domains.enums.TipoCliente;
import com.wsmarket.wsmarketbackend.dtos.ClienteDTO;
import com.wsmarket.wsmarketbackend.dtos.ClienteNewDTO;

@Component
@Scope("singleton")
public class ClienteMapper {
	public Cliente mapToCliente(ClienteDTO clienteDto) {
		return new Cliente(
			clienteDto.getId(),
			clienteDto.getNome(),
			clienteDto.getEmail(),
			null,
			null
		);
	}

	public ClienteDTO mapToClienteDTO(Cliente cliente) {
		return new ClienteDTO(
			cliente.getId(),
			cliente.getNome(),
			cliente.getEmail()
		);
	}

	public Cliente mapToCliente(ClienteNewDTO clienteNewDto) {
		Cliente cliente = new Cliente(
			clienteNewDto.getId(),
			clienteNewDto.getNome(),
			clienteNewDto.getEmail(),
			clienteNewDto.getCpfOuCnpj(),
			TipoCliente.toEnum(clienteNewDto.getTipo())
		);

		Cidade cidade = new Cidade(
			clienteNewDto.getCidadeId(),
			null,
			null
		);

		Endereco endereco = new Endereco(
			null,
			clienteNewDto.getLogradouro(),
			clienteNewDto.getNumero(),
			clienteNewDto.getComplemento(),
			clienteNewDto.getBairro(),
			clienteNewDto.getCep(),
			cliente,
			cidade
		);

		cliente.getEnderecos().add(endereco);
		cliente.getTelefones().addAll(clienteNewDto.getTelefones());

		return cliente;
	}

	public ClienteNewDTO mapToClienteNewDTO(Cliente cliente) {
		return new ClienteNewDTO(
			cliente.getId(),
			cliente.getNome(),
			cliente.getEmail(),
			cliente.getCpfOuCnpj(),
			cliente.getTipo().getCodigo(),
			cliente.getEnderecos().get(cliente.getEnderecos().size() - 1).getLogradouro(),
			cliente.getEnderecos().get(cliente.getEnderecos().size() - 1).getNumero(),
			cliente.getEnderecos().get(cliente.getEnderecos().size() - 1).getComplemento(),
			cliente.getEnderecos().get(cliente.getEnderecos().size() - 1).getBairro(),
			cliente.getEnderecos().get(cliente.getEnderecos().size() - 1).getCep(),
			cliente.getEnderecos().get(cliente.getEnderecos().size() - 1).getCidade().getId()
		);
	}

	public Cliente mapToNewCliente(
		Cliente novoCliente,
		Cliente cliente
	) {
		novoCliente.setId(cliente.getId());
		novoCliente.setNome(cliente.getNome());
		novoCliente.setEmail(cliente.getEmail());
		novoCliente.setCpfOuCnpj(cliente.getCpfOuCnpj());
		novoCliente.setTipo(cliente.getTipo());

		return novoCliente;	
	}
}
