package com.wsmarket.wsmarketbackend.services.interfaces;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.wsmarket.wsmarketbackend.domains.Categoria;
import com.wsmarket.wsmarketbackend.dtos.CategoriaDto;

public interface ICategoriaService {
	Page<CategoriaDto> findAll(Pageable pageable);
	Page<CategoriaDto> findPage(Integer page, Integer linesPerPage, String orderBy, String direction);
	Categoria findById(Long id);
	CategoriaDto create(Categoria categoria);
	CategoriaDto update(Categoria categoria);
	void delete(Long id);
}
