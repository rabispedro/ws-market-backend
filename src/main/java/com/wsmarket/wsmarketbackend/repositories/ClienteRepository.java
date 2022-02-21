package com.wsmarket.wsmarketbackend.repositories;

import com.wsmarket.wsmarketbackend.domains.Cliente;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ClienteRepository extends JpaRepository<Cliente, Long> {
	
}
