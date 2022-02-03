package com.wsmarket.wsmarketbackend.repositories;

import com.wsmarket.wsmarketbackend.domain.Pedido;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PedidoRepository extends JpaRepository<Pedido, Long> {
	
}
