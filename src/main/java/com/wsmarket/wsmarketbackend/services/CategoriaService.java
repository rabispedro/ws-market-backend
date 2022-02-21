package com.wsmarket.wsmarketbackend.services;

import java.util.Optional;

import com.wsmarket.wsmarketbackend.domains.Categoria;
import com.wsmarket.wsmarketbackend.dtos.CategoriaDTO;
import com.wsmarket.wsmarketbackend.repositories.CategoriaRepository;
import com.wsmarket.wsmarketbackend.services.exceptions.DataIntegrityException;
import com.wsmarket.wsmarketbackend.services.exceptions.ObjectNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class CategoriaService {
	@Autowired
	private CategoriaRepository categoriaRepository;
	
	public Page<CategoriaDTO> findAll(Pageable pageable) {
		Page<Categoria> categorias = this.categoriaRepository.findAll(pageable);
		return categorias.map(categoria -> new CategoriaDTO(categoria));
	}
	
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

	public Categoria create(Categoria categoria) {
		Categoria newCategoria = this.categoriaRepository.save(categoria);

		return newCategoria;
	}

	public Categoria update(Long id, Categoria categoria) {
		this.findById(id);
		categoria.setId(id);

		Categoria updatedCategoria = this.categoriaRepository.save(categoria);

		return updatedCategoria;
	}

	public void delete(Long id) {
		this.findById(id);

		try {
			this.categoriaRepository.deleteById(id);
		} catch (DataIntegrityViolationException exception) {
			throw new DataIntegrityException(
				"It's not possible to delete a 'Categoria' with relations."
			);
		}

		return;
	}
}
