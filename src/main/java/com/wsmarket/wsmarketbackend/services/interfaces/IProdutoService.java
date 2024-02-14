package com.wsmarket.wsmarketbackend.services.interfaces;

import java.util.List;

import org.springframework.data.domain.Page;

import com.wsmarket.wsmarketbackend.domains.Produto;
import com.wsmarket.wsmarketbackend.dtos.ProdutoDto;

public interface IProdutoService {
	Page<ProdutoDto> search(String nome, List<Long> ids, Integer page, Integer linesPerPage, String orderBy, String direction);
	Produto findById(Long id);
}
