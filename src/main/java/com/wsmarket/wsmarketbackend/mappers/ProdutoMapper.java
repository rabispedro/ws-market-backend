package com.wsmarket.wsmarketbackend.mappers;

import com.wsmarket.wsmarketbackend.domains.Produto;
import com.wsmarket.wsmarketbackend.dtos.ProdutoDto;
import com.wsmarket.wsmarketbackend.mappers.interfaces.IProdutoMapper;

public class ProdutoMapper extends BaseMapper implements IProdutoMapper {
	public ProdutoDto mapToProdutoDto(Produto produto) {
		return new ProdutoDto(produto.getId(), produto.getNome(), produto.getPreco());
	}

	public Produto mapToProduto(ProdutoDto produtoDto) {
		return new Produto(produtoDto.id(), produtoDto.nome(), produtoDto.preco());
	}
}
