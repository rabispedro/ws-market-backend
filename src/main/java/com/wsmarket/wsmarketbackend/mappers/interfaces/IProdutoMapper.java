package com.wsmarket.wsmarketbackend.mappers.interfaces;

import com.wsmarket.wsmarketbackend.domains.Produto;
import com.wsmarket.wsmarketbackend.dtos.ProdutoDto;

public interface IProdutoMapper {
	ProdutoDto mapToProdutoDto(Produto produto);
	Produto mapToProduto(ProdutoDto produtoDto);
}
