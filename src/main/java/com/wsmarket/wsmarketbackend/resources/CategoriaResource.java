package com.wsmarket.wsmarketbackend.resources;

import java.net.URI;

import javax.validation.Valid;

import com.wsmarket.wsmarketbackend.domains.Categoria;
import com.wsmarket.wsmarketbackend.dtos.CategoriaDto;
import com.wsmarket.wsmarketbackend.mappers.interfaces.ICategoriaMapper;
import com.wsmarket.wsmarketbackend.services.interfaces.ICategoriaService;

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
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

@RequestMapping(path = "/categorias")
public class CategoriaResource extends BaseResource {
	private final ICategoriaService _categoriaService;
	private final ICategoriaMapper _categoriaMapper;

	public CategoriaResource(
		@Autowired ICategoriaService categoriaService,
		@Autowired ICategoriaMapper categoriaMapper
	) {
		_categoriaService = categoriaService;
		_categoriaMapper = categoriaMapper;
	}

	@GetMapping(path = "")
	public ResponseEntity<Page<CategoriaDto>> findAll(Pageable pageable) {
		return ResponseEntity.ok(_categoriaService.findAll(pageable));
	}

	@GetMapping(path = "/pagination")
	public ResponseEntity<Page<CategoriaDto>> findPage(
		@RequestParam(name = "page", defaultValue = "0") Integer page,
		@RequestParam(name = "limit", defaultValue = "24") Integer linesPerPage,
		@RequestParam(name = "orderBy", defaultValue = "nome") String orderBy,
		@RequestParam(name = "direction", defaultValue = "ASC") String direction
	) {
		return ResponseEntity.ok(_categoriaService.findPage(page, linesPerPage, orderBy, direction));
	}
	
	@GetMapping(path = "/{id}")
	public ResponseEntity<Categoria> findById(@PathVariable Long id) {
		return ResponseEntity.ok(_categoriaService.findById(id));
	}

	@PostMapping(path = "")
	public ResponseEntity<Void> create(@Valid @RequestBody CategoriaDto categoriaDto) {
		CategoriaDto categoria = _categoriaService.create(_categoriaMapper.mapToCategoria(categoriaDto));
		
		URI uri = ServletUriComponentsBuilder
			.fromCurrentRequest()
			.path("/{id}")
			.buildAndExpand(categoria.id())
			.toUri();

		return ResponseEntity.created(uri).build();
	}

	@PutMapping(path = "/{id}")
	public ResponseEntity<Void> update(
		@PathVariable Long id,
		@Valid @RequestBody CategoriaDto categoriaDto
	) {
		_categoriaService.update(_categoriaMapper.mapToCategoria(categoriaDto));
		return ResponseEntity.noContent().build();
	}

	@DeleteMapping(path = "/{id}")
	public ResponseEntity<Void> delete(@PathVariable Long id) {
		_categoriaService.delete(id);
		return ResponseEntity.noContent().build();
	}
}
