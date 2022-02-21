package com.wsmarket.wsmarketbackend.resources;

import java.net.URI;

import com.wsmarket.wsmarketbackend.domain.Categoria;
import com.wsmarket.wsmarketbackend.services.CategoriaService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

@RestController
@RequestMapping(path = "/categorias")
public class CategoriaResource {
	@Autowired
	private CategoriaService categoriaService;

	@GetMapping(path = "/{id}")
	public ResponseEntity<Categoria> findById(@PathVariable Long id) {
		Categoria categoria = this.categoriaService.findById(id);
		return ResponseEntity.ok(categoria);
	}

	@PostMapping(path = "/")
	public ResponseEntity<Void> create(@RequestBody Categoria categoria) {
		Categoria newCategoria = this.categoriaService.create(categoria);
		
		URI uri = ServletUriComponentsBuilder
			.fromCurrentRequest()
			.path("/{id}")
			.buildAndExpand(newCategoria.getId())
			.toUri();

		return ResponseEntity.created(uri).build();
	}
}
