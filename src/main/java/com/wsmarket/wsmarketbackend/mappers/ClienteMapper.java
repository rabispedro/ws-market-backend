package com.wsmarket.wsmarketbackend.mappers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import com.wsmarket.wsmarketbackend.domains.Cidade;
import com.wsmarket.wsmarketbackend.domains.Cliente;
import com.wsmarket.wsmarketbackend.domains.Endereco;
import com.wsmarket.wsmarketbackend.domains.enums.TipoCliente;
import com.wsmarket.wsmarketbackend.dtos.ClienteDto;
import com.wsmarket.wsmarketbackend.dtos.ClienteNewDto;
import com.wsmarket.wsmarketbackend.mappers.interfaces.IClienteMapper;

public class ClienteMapper extends BaseMapper implements IClienteMapper {
	private final BCryptPasswordEncoder _bCryptPasswordEncoder;

	public ClienteMapper(
		@Autowired BCryptPasswordEncoder bCryptPasswordEncoder
	) {
		_bCryptPasswordEncoder = bCryptPasswordEncoder;
	}
	
	public Cliente mapToCliente(ClienteDto clienteDto) {
		return new Cliente(
			clienteDto.id(),
			clienteDto.nome(),
			clienteDto.email(),
			null,
			null,
			null
		);
	}

	public ClienteDto mapToClienteDto(Cliente cliente) {
		return new ClienteDto(cliente.getId(), cliente.getNome(), cliente.getEmail());
	}

	public Cliente mapToCliente(ClienteNewDto clienteNewDto) {
		Cliente cliente = new Cliente(
			clienteNewDto.id(),
			clienteNewDto.nome(),
			clienteNewDto.email(),
			clienteNewDto.cpfOuCnpj(),
			TipoCliente.toEnum(clienteNewDto.tipo()),
			_bCryptPasswordEncoder.encode(clienteNewDto.senha())
		);

		Cidade cidade = new Cidade(
			clienteNewDto.cidadeId(),
			null,
			null
		);

		Endereco endereco = new Endereco(
			null,
			clienteNewDto.logradouro(),
			clienteNewDto.numero(),
			clienteNewDto.complemento(),
			clienteNewDto.bairro(),
			clienteNewDto.cep(),
			cliente,
			cidade
		);

		cliente.getEnderecos().add(endereco);
		cliente.getTelefones().addAll(clienteNewDto.telefones());

		return cliente;
	}

	public ClienteNewDto mapToClienteNewDto(Cliente cliente) {
		return new ClienteNewDto(
			cliente.getId(),
			cliente.getNome(),
			cliente.getEmail(),
			cliente.getCpfOuCnpj(),
			cliente.getTipo().getCodigo(),
			null,
			cliente.getEnderecos().get(cliente.getEnderecos().size() - 1).getLogradouro(),
			cliente.getEnderecos().get(cliente.getEnderecos().size() - 1).getNumero(),
			cliente.getEnderecos().get(cliente.getEnderecos().size() - 1).getComplemento(),
			cliente.getEnderecos().get(cliente.getEnderecos().size() - 1).getBairro(),
			cliente.getEnderecos().get(cliente.getEnderecos().size() - 1).getCep(),
			null,
			cliente.getEnderecos().get(cliente.getEnderecos().size() - 1).getCidade().getId()
		);
	}

	public Cliente mapToNewCliente(
		Cliente novoCliente,
		Cliente cliente
	) {
		novoCliente.setId(cliente.getId() != null ? cliente.getId() : novoCliente.getId());
		novoCliente.setNome(cliente.getNome() != null ? cliente.getNome() : novoCliente.getNome());
		novoCliente.setEmail(cliente.getEmail() != null ? cliente.getEmail() : novoCliente.getEmail());
		novoCliente.setCpfOuCnpj(cliente.getCpfOuCnpj() != null ? cliente.getCpfOuCnpj() : novoCliente.getCpfOuCnpj());
		novoCliente.setTipo(cliente.getTipo() != null ? cliente.getTipo() : novoCliente.getTipo());

		return novoCliente;
	}
}
