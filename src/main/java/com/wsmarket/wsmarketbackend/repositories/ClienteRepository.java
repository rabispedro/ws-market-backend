package com.wsmarket.wsmarketbackend.repositories;

import com.wsmarket.wsmarketbackend.domains.Cliente;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public interface ClienteRepository extends JpaRepository<Cliente, Long> {
	@Transactional(readOnly = true)
	public Cliente findByEmail(String email);
}
