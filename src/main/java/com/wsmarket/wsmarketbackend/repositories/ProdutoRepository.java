package com.wsmarket.wsmarketbackend.repositories;

import com.wsmarket.wsmarketbackend.domains.Categoria;
import com.wsmarket.wsmarketbackend.domains.Produto;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public interface ProdutoRepository extends JpaRepository<Produto, Long> {
	
	@Transactional(readOnly = true)
	@Query(
		"SELECT DISTINCT p " +
		"FROM Produto p " +
		"INNER JOIN p.categorias c " +
		"WHERE " +
		"p.nome LIKE %:nome% AND " +
		"c IN :categorias"
	)
	public Page<Produto> search(
		@Param("nome") String nome,
		@Param("categorias") List<Categoria> categorias,
		Pageable pageRequest
	);
}
