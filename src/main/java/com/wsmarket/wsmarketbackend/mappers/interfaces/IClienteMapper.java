package com.wsmarket.wsmarketbackend.mappers.interfaces;

import com.wsmarket.wsmarketbackend.domains.Cliente;
import com.wsmarket.wsmarketbackend.dtos.ClienteDto;
import com.wsmarket.wsmarketbackend.dtos.ClienteNewDto;

public interface IClienteMapper {
	Cliente mapToCliente(ClienteDto clienteDto);
	ClienteDto mapToClienteDto(Cliente cliente);
	Cliente mapToCliente(ClienteNewDto clienteNewDto);
	ClienteNewDto mapToClienteNewDto(Cliente cliente);
	Cliente mapToNewCliente(Cliente novoCliente, Cliente cliente);
}
