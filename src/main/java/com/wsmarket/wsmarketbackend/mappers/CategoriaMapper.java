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

	public Categoria mapToCategoria(CategoriaDTO categoriaDto) {
		return new Categoria(
			categoriaDto.getId(),
			categoriaDto.getNome()
		);
	}

	public Categoria mapToNewCategoria(
		Categoria novaCategoria,
		Categoria categoria
	) {
		novaCategoria.setId(categoria.getId() != null ? categoria.getId() : novaCategoria.getId());
		novaCategoria.setNome(categoria.getNome() != null ? categoria.getNome() : novaCategoria.getNome());
		novaCategoria.setProdutos(categoria.getProdutos() != null ? categoria.getProdutos() : novaCategoria.getProdutos());

		return novaCategoria;
	}
}
