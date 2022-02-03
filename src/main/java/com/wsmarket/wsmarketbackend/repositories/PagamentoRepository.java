package com.wsmarket.wsmarketbackend.repositories;

import com.wsmarket.wsmarketbackend.domain.Pagamento;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PagamentoRepository extends JpaRepository<Pagamento, Long> {
	
}
