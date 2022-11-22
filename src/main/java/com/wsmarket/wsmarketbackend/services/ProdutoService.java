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
import com.wsmarket.wsmarketbackend.dtos.ProdutoDTO;
import com.wsmarket.wsmarketbackend.mappers.ProdutoMapper;
import com.wsmarket.wsmarketbackend.repositories.CategoriaRepository;
import com.wsmarket.wsmarketbackend.repositories.ProdutoRepository;
import com.wsmarket.wsmarketbackend.services.exceptions.ObjectNotFoundException;

@Service
public class ProdutoService {
	@Autowired
	private ProdutoRepository produtoRepository;

	@Autowired
	private CategoriaRepository categoriaRepository;

	@Autowired
	private ProdutoMapper produtoMapper;

	@Transactional
	public Page<ProdutoDTO> search(
		String nome,
		List<Long> ids,
		Integer page,
		Integer linesPerPage,
		String orderBy,
		String direction
	) {
		PageRequest pageRequest = PageRequest
			.of(page, linesPerPage, Direction.valueOf(direction), orderBy);

		List<Categoria> categorias = this.categoriaRepository.findAllById(ids);
		Page<Produto> produtos = this.produtoRepository.search(nome, categorias, pageRequest);

		return produtos.map(produto -> this.produtoMapper.mapToProdutoDTO(produto));
	}

	public Produto findById(Long id) {
		Optional<Produto> produto = this.produtoRepository.findById(id);

		return produto.orElseThrow(() -> (
			new ObjectNotFoundException(
				"Objeto não encontrado! " +
				"Id: " + id + ", " +
				"Tipo: " + Produto.class.getTypeName() + "."
			)
		));
	}
}
