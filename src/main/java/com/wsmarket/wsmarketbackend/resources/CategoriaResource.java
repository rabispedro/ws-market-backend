package com.wsmarket.wsmarketbackend.resources;

import java.net.URI;

import javax.validation.Valid;

import com.wsmarket.wsmarketbackend.domains.Categoria;
import com.wsmarket.wsmarketbackend.dtos.CategoriaDTO;
import com.wsmarket.wsmarketbackend.services.CategoriaService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

@RestController
@RequestMapping(path = "/categorias")
public class CategoriaResource {
	@Autowired
	private CategoriaService categoriaService;

	@GetMapping(path = "")
	public ResponseEntity<Page<CategoriaDTO>> findAll(Pageable pageable) {
		Page<CategoriaDTO> categorias = this.categoriaService.findAll(pageable);
		return ResponseEntity.ok(categorias);
	}

	@GetMapping(path = "/pagination")
	public ResponseEntity<Page<Categoria>> findPage(
		@RequestParam(name = "page", defaultValue = "0") Integer page,
		@RequestParam(name = "limit", defaultValue = "24") Integer linesPerPage,
		@RequestParam(name = "orderBy", defaultValue = "nome") String orderBy,
		@RequestParam(name = "direction", defaultValue = "ASC") String direction
	) {
		Page<Categoria> categorias = this.categoriaService.findPage(
			page,
			linesPerPage,
			orderBy,
			direction
		);
		return ResponseEntity.ok(categorias);
	}
	
	@GetMapping(path = "/{id}")
	public ResponseEntity<CategoriaDTO> findById(
		@PathVariable Long id
	) {
		CategoriaDTO categoria = this.categoriaService.findById(id);
		return ResponseEntity.ok(categoria);
	}

	@PostMapping(path = "")
	public ResponseEntity<Void> create(
		@Valid @RequestBody CategoriaDTO categoriaDto
	) {
		Categoria newCategoria = this.categoriaService
			.create(categoriaDto.fromDTO());
		
		URI uri = ServletUriComponentsBuilder
			.fromCurrentRequest()
			.path("/{id}")
			.buildAndExpand(newCategoria.getId())
			.toUri();

		return ResponseEntity.created(uri).build();
	}

	@PutMapping(path = "/{id}")
	public ResponseEntity<Void> update(
		@PathVariable Long id,
		@Valid @RequestBody CategoriaDTO categoriaDto
	) {
		this.categoriaService.update(id, categoriaDto.fromDTO());
		return ResponseEntity.noContent().build();
	}

	@DeleteMapping(path = "/{id}")
	public ResponseEntity<Void> delete(
		@PathVariable Long id
	) {
		this.categoriaService.delete(id);
		return ResponseEntity.noContent().build();
	}
}
