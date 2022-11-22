package com.wsmarket.wsmarketbackend.mappers;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.wsmarket.wsmarketbackend.domains.Produto;
import com.wsmarket.wsmarketbackend.dtos.ProdutoDTO;

@Component
@Scope("singleton")
public class ProdutoMapper {
	public ProdutoDTO mapToProdutoDTO(Produto produto) {
		return new ProdutoDTO(
			produto.getId(),
			produto.getNome(),
			produto.getPreco()
		);
	}

	public Produto mapToProduto(ProdutoDTO produtoDto) {
		return new Produto(
			produtoDto.getId(),
			produtoDto.getNome(),
			produtoDto.getPreco()
		);
	}
}
