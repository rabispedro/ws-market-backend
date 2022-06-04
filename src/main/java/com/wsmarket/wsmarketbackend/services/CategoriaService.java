package com.wsmarket.wsmarketbackend.services;

import com.wsmarket.wsmarketbackend.domains.Categoria;
import com.wsmarket.wsmarketbackend.dtos.CategoriaDTO;
import com.wsmarket.wsmarketbackend.repositories.CategoriaRepository;
import com.wsmarket.wsmarketbackend.services.exceptions.DataIntegrityException;
import com.wsmarket.wsmarketbackend.services.exceptions.ObjectNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;

@Service
public class CategoriaService {
	@Autowired
	private CategoriaRepository categoriaRepository;
	
	public Page<CategoriaDTO> findAll(Pageable pageable) {
		Page<Categoria> categorias = this.categoriaRepository.findAll(pageable);
		return categorias.map(categoria -> new CategoriaDTO(categoria));
	}

	public Page<CategoriaDTO> findPage(
		Integer page,
		Integer linesPerPage,
		String orderBy,
		String direction
		) {
		PageRequest pageRequest = PageRequest
			.of(page, linesPerPage, Direction.valueOf(direction), orderBy)
		;

		Page<Categoria> categorias = this.categoriaRepository.findAll(pageRequest);
		return categorias.map(categoria -> new CategoriaDTO(categoria));
	}
	
	public Categoria findById(Long id) {
		Categoria categoria = this.categoriaRepository.findById(id)
			.orElseThrow(() -> (
				new ObjectNotFoundException(
					"Objeto nao encontrado! " +
					"Id: " + id + ", " +
					"Tipo: " + Categoria.class.getName() + "."
				)
			))
		;

		return categoria;
	}

	public CategoriaDTO create(Categoria categoria) {
		Categoria newCategoria = this.categoriaRepository.save(categoria);

		return new CategoriaDTO(newCategoria);
	}

	public CategoriaDTO update(Categoria categoria) {
		Categoria newCategoria = this.findById(categoria.getId());
		this.updateCategoriaData(newCategoria, categoria);
		Categoria updatedCategoria = this.categoriaRepository.save(newCategoria);

		return new CategoriaDTO(updatedCategoria);
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

	private void updateCategoriaData(
		Categoria categoria,
		Categoria formerCategoria
	) {
			categoria.setNome(formerCategoria.getNome());
			categoria.setProdutos(formerCategoria.getProdutos());
		return;
	}
}
