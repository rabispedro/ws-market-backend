package com.wsmarket.wsmarketbackend.resources;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "/categorias")
public class CategoriaResource {

	@GetMapping(path = "")
	public String listar() {
		return "REST está funcionando!";
	}
}
