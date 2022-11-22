package com.wsmarket.wsmarketbackend.mappers;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.wsmarket.wsmarketbackend.domains.Categoria;
import com.wsmarket.wsmarketbackend.dtos.CategoriaDTO;

@Component
@Scope("singleton")
public class CategoriaMapper {
	public CategoriaDTO mapToCategoriaDTO(Categoria categoria) {
		return new CategoriaDTO(
			categoria.getId(),
			categoria.getNome()
		);
	}

	public Categoria mapToCategoria(CategoriaDTO categoriaDTO) {
		return new Categoria(
			categoriaDTO.getId(),
			categoriaDTO.getNome()
		);
	}
}
