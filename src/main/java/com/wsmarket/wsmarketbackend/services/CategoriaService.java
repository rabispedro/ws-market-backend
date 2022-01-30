package com.wsmarket.wsmarketbackend.services;

import com.wsmarket.wsmarketbackend.domain.Categoria;
import com.wsmarket.wsmarketbackend.repositories.CategoriaRepository;

import org.springframework.stereotype.Service;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class CategoriaService {
	final private CategoriaRepository categoriaRepository;
	
	public Categoria findById(Long id) {
		return this.categoriaRepository.findById(id).get();
	}
}
