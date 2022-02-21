package com.wsmarket.wsmarketbackend.repositories;

import com.wsmarket.wsmarketbackend.domains.Produto;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProdutoRepository extends JpaRepository<Produto, Long> {
	
}
