package com.wsmarket.wsmarketbackend.services;

import com.wsmarket.wsmarketbackend.domains.Categoria;
import com.wsmarket.wsmarketbackend.dtos.CategoriaDto;
import com.wsmarket.wsmarketbackend.mappers.interfaces.ICategoriaMapper;
import com.wsmarket.wsmarketbackend.repositories.CategoriaRepository;
import com.wsmarket.wsmarketbackend.services.exceptions.DataIntegrityException;
import com.wsmarket.wsmarketbackend.services.exceptions.ObjectNotFoundException;
import com.wsmarket.wsmarketbackend.services.interfaces.ICategoriaService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort.Direction;

public class CategoriaService extends BaseService implements ICategoriaService {
	private final CategoriaRepository _categoriaRepository;
	private final ICategoriaMapper _categoriaMapper;

	public CategoriaService(
		@Autowired CategoriaRepository categoriaRepository,
		@Autowired ICategoriaMapper categoriaMapper
	) {
		_categoriaRepository = categoriaRepository;
		_categoriaMapper = categoriaMapper;
	}
	
	public Page<CategoriaDto> findAll(Pageable pageable) {
		Page<Categoria> categorias = _categoriaRepository.findAll(pageable);

		return categorias.map(_categoriaMapper::mapToCategoriaDto);
	}

	public Page<CategoriaDto> findPage(
		Integer page,
		Integer linesPerPage,
		String orderBy,
		String direction) {
		PageRequest pageRequest = PageRequest
			.of(page, linesPerPage, Direction.valueOf(direction), orderBy);

		Page<Categoria> categorias = _categoriaRepository.findAll(pageRequest);

		return categorias.map(_categoriaMapper::mapToCategoriaDto);
	}
	
	public Categoria findById(Long id) {
		Categoria categoria = _categoriaRepository.findById(id)
			.orElseThrow(() -> (
				new ObjectNotFoundException(
					"Objeto nao encontrado! " +
					"Id: " + id + ", " +
					"Tipo: " + Categoria.class.getName() + ".")));

		return categoria;
	}

	public CategoriaDto create(Categoria categoria) {
		categoria.setId(null);
		Categoria newCategoria = _categoriaRepository.save(categoria);

		return _categoriaMapper.mapToCategoriaDto(newCategoria);
	}

	public CategoriaDto update(Categoria categoria) {
		Categoria newCategoria = this.findById(categoria.getId());
		_categoriaMapper.mapToNewCategoria(newCategoria, categoria);
		Categoria updatedCategoria = _categoriaRepository.save(newCategoria);

		return _categoriaMapper.mapToCategoriaDto(updatedCategoria);
	}

	public void delete(Long id) {
		this.findById(id);

		try {
			_categoriaRepository.deleteById(id);
		} catch (DataIntegrityViolationException exception) {
			throw new DataIntegrityException(
				"It's not possible to delete a 'Categoria' with relations."
			);
		}
	}
}
