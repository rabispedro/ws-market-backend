package com.wsmarket.wsmarketbackend.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.wsmarket.wsmarketbackend.domains.Categoria;
import com.wsmarket.wsmarketbackend.domains.Produto;
import com.wsmarket.wsmarketbackend.dtos.ProdutoDto;
import com.wsmarket.wsmarketbackend.mappers.ProdutoMapper;
import com.wsmarket.wsmarketbackend.mappers.interfaces.IProdutoMapper;
import com.wsmarket.wsmarketbackend.repositories.CategoriaRepository;
import com.wsmarket.wsmarketbackend.repositories.ProdutoRepository;
import com.wsmarket.wsmarketbackend.services.exceptions.ObjectNotFoundException;
import com.wsmarket.wsmarketbackend.services.interfaces.IProdutoService;

public class ProdutoService extends BaseService implements IProdutoService {
	private final ProdutoRepository _produtoRepository;
	private final CategoriaRepository _categoriaRepository;
	private final IProdutoMapper _produtoMapper;

	public ProdutoService(
		@Autowired ProdutoRepository produtoRepository,
		@Autowired CategoriaRepository categoriaRepository,
		@Autowired IProdutoMapper produtoMapper
	) {
		_produtoRepository = produtoRepository;
		_categoriaRepository = categoriaRepository;
		_produtoMapper = produtoMapper;
	}

	@Transactional
	public Page<ProdutoDto> search(
		String nome,
		List<Long> ids,
		Integer page,
		Integer linesPerPage,
		String orderBy,
		String direction
	) {
		PageRequest pageRequest = PageRequest
			.of(page, linesPerPage, Direction.valueOf(direction), orderBy);

		List<Categoria> categorias = _categoriaRepository.findAllById(ids);
		Page<Produto> produtos = _produtoRepository.search(nome, categorias, pageRequest);

		return produtos.map(_produtoMapper::mapToProdutoDto);
	}

	public Produto findById(Long id) {
		Optional<Produto> produto = _produtoRepository.findById(id);

		return produto.orElseThrow(() -> (
			new ObjectNotFoundException(
				"Objeto não encontrado! " +
				"Id: " + id + ", " +
				"Tipo: " + Produto.class.getTypeName() + ".")));
	}
}
