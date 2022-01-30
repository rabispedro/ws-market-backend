package com.wsmarket.wsmarketbackend.repositories;

import com.wsmarket.wsmarketbackend.domain.Produto;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProdutoRepository extends JpaRepository<Produto, Long> {
	
}
