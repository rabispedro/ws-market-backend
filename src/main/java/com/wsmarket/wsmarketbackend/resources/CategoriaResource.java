package com.wsmarket.wsmarketbackend.resources;

import java.util.ArrayList;
import java.util.List;

import com.wsmarket.wsmarketbackend.domain.Categoria;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "/categorias")
public class CategoriaResource {

	@GetMapping(path = "")
	public List<Categoria> listar() {
		Categoria cat1 = new Categoria(1L, "Informática");
		Categoria cat2 = new Categoria(2L, "Escritório");

		List<Categoria> listaCategorias = new ArrayList<Categoria>();
		listaCategorias.add(cat1);
		listaCategorias.add(cat2);

		return listaCategorias;
	}
}
