package com.wsmarket.wsmarketbackend.resources;

import com.wsmarket.wsmarketbackend.domain.Categoria;
import com.wsmarket.wsmarketbackend.services.CategoriaService;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.AllArgsConstructor;

@RestController
@RequestMapping(path = "/categorias")
@AllArgsConstructor
public class CategoriaResource {
	final private CategoriaService categoriaService;

	@GetMapping(path = "/{id}")
	public ResponseEntity<Categoria> findById(@PathVariable Long id) {
		Categoria categoria = this.categoriaService.findById(id);
		return ResponseEntity.ok(categoria);
	}
}
