package com.wsmarket.wsmarketbackend.mappers.interfaces;

import com.wsmarket.wsmarketbackend.domains.Categoria;
import com.wsmarket.wsmarketbackend.dtos.CategoriaDto;

public interface ICategoriaMapper {
	CategoriaDto mapToCategoriaDto(Categoria categoria);
	Categoria mapToCategoria(CategoriaDto categoriaDto);
	Categoria mapToNewCategoria(Categoria novaCategoria, Categoria categoria);
}
