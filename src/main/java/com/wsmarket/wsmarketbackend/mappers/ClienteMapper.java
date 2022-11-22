package com.wsmarket.wsmarketbackend.mappers;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.wsmarket.wsmarketbackend.domains.Cliente;
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
			null,
			clienteNewDto.getNome(),
			clienteNewDto.getEmail(),
			// clienteNewDto.getCpfOuCnpj(),
			null,
			null
		);

		// Cidade cidade = new Cidade(
		// 	clienteNewDto.getCidadeId(),
		// 	null,
		// 	null
		// );

		// Endereco endereco = new Endereco(
		// 	null,
		// 	clienteNewDto.getLogradouro(),
		// 	clienteNewDto.getNumero(),
		// 	clienteNewDto.getCep()
		// );

		// cliente.getEnderecos().add(endereco);
		// cliente.getTelefones().add(clienteNewDto.getTelefone1());

		// if(clienteNewDto.getTelefone2() != null) {
		// 	cliente.getTelefones().add(clienteNewDto.getTelefone2());
		// }

		// if(clienteNewDto.getTelefone3() != null) {
		// 	cliente.getTelefones().add(clienteNewDto.getTelefone3());
		// }

		return cliente;
	}

	public ClienteNewDTO mapToClienteNewDTO(Cliente cliente) {
		return new ClienteNewDTO(
			cliente.getNome(),
			cliente.getEmail(),
			cliente.getCpfOuCnpj(),
			cliente.getTipo().getCodigo(),
			cliente.getEnderecos().get(0).getLogradouro(),
			cliente.getEnderecos().get(0).getNumero(),
			cliente.getEnderecos().get(0).getComplemento(),
			cliente.getEnderecos().get(0).getBairro(),
			cliente.getEnderecos().get(0).getCep(),
			// cliente.getTelefones().forEach((x) -> x.get),
			"",
			cliente.getEnderecos().get(0).getCidade().getId()
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
