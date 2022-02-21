package com.wsmarket.wsmarketbackend.repositories;

import com.wsmarket.wsmarketbackend.domains.ItemPedido;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ItemPedidoRepository extends JpaRepository<ItemPedido, Long> {
	
}
