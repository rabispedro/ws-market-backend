package com.wsmarket.wsmarketbackend.services.interfaces;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.wsmarket.wsmarketbackend.domains.Cliente;
import com.wsmarket.wsmarketbackend.dtos.ClienteDto;

public interface IClienteService {
	Page<ClienteDto> findAll(Pageable pageable);
	Page<ClienteDto> findPage(Integer page, Integer linesPerPage, String orderBy, String direction);
	Cliente findById(Long id);
	ClienteDto create(Cliente cliente);
	ClienteDto update(Long id, Cliente cliente);
	void delete(Long id);
}
