package com.wsmarket.wsmarketbackend.services;

import com.wsmarket.wsmarketbackend.domain.Categoria;
import com.wsmarket.wsmarketbackend.repositories.CategoriaRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CategoriaService {
	@Autowired
	private CategoriaRepository categoriaRepository;
	
	public Categoria findById(Long id) {
		return this.categoriaRepository.findById(id).get();
	}
}
