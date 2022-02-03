package com.wsmarket.wsmarketbackend.services;

import java.util.Optional;

import com.wsmarket.wsmarketbackend.domain.Categoria;
import com.wsmarket.wsmarketbackend.repositories.CategoriaRepository;
import com.wsmarket.wsmarketbackend.services.exceptions.ObjectNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CategoriaService {
	@Autowired
	private CategoriaRepository categoriaRepository;
	
	public Categoria findById(Long id) {
		Optional<Categoria> categoria = this.categoriaRepository.findById(id);

		return categoria.orElseThrow(() -> (
			new ObjectNotFoundException(
				"Objeto nao encontrado! " +
				"Id: " + id + ", " +
				"Tipo: " + Categoria.class.getName() + "."
			)
		));
	}
}
