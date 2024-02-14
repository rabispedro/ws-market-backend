package com.wsmarket.wsmarketbackend.mappers;

import com.wsmarket.wsmarketbackend.domains.Categoria;
import com.wsmarket.wsmarketbackend.dtos.CategoriaDto;
import com.wsmarket.wsmarketbackend.mappers.interfaces.ICategoriaMapper;

public class CategoriaMapper extends BaseMapper implements ICategoriaMapper {
	public CategoriaDto mapToCategoriaDto(Categoria categoria) {
		return new CategoriaDto(categoria.getId(), categoria.getNome());
	}

	public Categoria mapToCategoria(CategoriaDto categoriaDto) {
		return new Categoria(categoriaDto.id(), categoriaDto.nome());
	}

	public Categoria mapToNewCategoria(Categoria novaCategoria, Categoria categoria) {
		novaCategoria.setId(categoria.getId() != null ? categoria.getId() : novaCategoria.getId());
		novaCategoria.setNome(categoria.getNome() != null ? categoria.getNome() : novaCategoria.getNome());
		novaCategoria.setProdutos(categoria.getProdutos() != null ? categoria.getProdutos() : novaCategoria.getProdutos());

		return novaCategoria;
	}
}
