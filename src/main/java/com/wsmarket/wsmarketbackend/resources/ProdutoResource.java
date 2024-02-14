package com.wsmarket.wsmarketbackend.resources;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.wsmarket.wsmarketbackend.domains.Produto;
import com.wsmarket.wsmarketbackend.dtos.ProdutoDto;
import com.wsmarket.wsmarketbackend.resources.utils.QueryParamsUtil;
import com.wsmarket.wsmarketbackend.services.interfaces.IProdutoService;

@RequestMapping(path = "/produtos")
public class ProdutoResource extends BaseResource {
	private final IProdutoService _produtoService;

	public ProdutoResource(
		@Autowired IProdutoService produtoService
	) {
		_produtoService = produtoService;
	}

	@GetMapping(path = "/{id}")
	public ResponseEntity<Produto> findById(@Valid @PathVariable Long id) {
		return ResponseEntity.ok(_produtoService.findById(id));
	}

	@GetMapping(path = "/pagination")
	public ResponseEntity<Page<ProdutoDto>> findPage(
		@RequestParam(name = "nome", defaultValue = "") String nome,
		@RequestParam(name = "categorias", defaultValue = "") String categorias,
		@RequestParam(name = "page", defaultValue = "0") Integer page,
		@RequestParam(name = "limit", defaultValue = "24") Integer linesPerPage,
		@RequestParam(name = "orderBy", defaultValue = "nome") String orderBy,
		@RequestParam(name = "direction", defaultValue = "ASC") String direction
	) {
		return ResponseEntity.ok(_produtoService.search(
			QueryParamsUtil.decodeQueryParam(nome),
			QueryParamsUtil.decodeListBySeparator(categorias, ","),
			page,
			linesPerPage,
			orderBy,
			direction
		));
	}
}
