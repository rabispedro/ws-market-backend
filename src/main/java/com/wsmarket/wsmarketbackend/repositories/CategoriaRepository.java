package com.wsmarket.wsmarketbackend.repositories;

import com.wsmarket.wsmarketbackend.domain.Categoria;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CategoriaRepository extends JpaRepository<Categoria, Long> {

}
