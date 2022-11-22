package com.wsmarket.wsmarketbackend.resources;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.wsmarket.wsmarketbackend.domains.Produto;
import com.wsmarket.wsmarketbackend.dtos.ProdutoDTO;
import com.wsmarket.wsmarketbackend.resources.utils.QueryParamsUtil;
import com.wsmarket.wsmarketbackend.services.ProdutoService;

@RestController
@RequestMapping(path = "/produtos")
public class ProdutoResource {
	@Autowired
	private ProdutoService produtoService;

	@GetMapping(path = "/{id}")
	public ResponseEntity<Produto> findById(
		@Valid @PathVariable Long id
	) {
		Produto produto = this.produtoService.findById(id);

		return ResponseEntity.ok(produto);
	}

	@GetMapping(path = "/pagination")
	public ResponseEntity<Page<ProdutoDTO>> findPage(
		@RequestParam(name = "nome", defaultValue = "") String nome,
		@RequestParam(name = "categorias", defaultValue = "") String categorias,
		@RequestParam(name = "page", defaultValue = "0") Integer page,
		@RequestParam(name = "limit", defaultValue = "24") Integer linesPerPage,
		@RequestParam(name = "orderBy", defaultValue = "nome") String orderBy,
		@RequestParam(name = "direction", defaultValue = "ASC") String direction
	) {
		Page<ProdutoDTO> produtos = this.produtoService.search(
			QueryParamsUtil.decodeQueryParam(nome),
			QueryParamsUtil.decodeListBySeparator(categorias, ","),
			page,
			linesPerPage,
			orderBy,
			direction
		);

		return ResponseEntity.ok(produtos);
	}

}
