package com.wsmarket.wsmarketbackend;

import java.util.Arrays;

import com.wsmarket.wsmarketbackend.domain.Categoria;
import com.wsmarket.wsmarketbackend.repositories.CategoriaRepository;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import lombok.AllArgsConstructor;

@SpringBootApplication
@AllArgsConstructor
public class WsMarketBackendApplication implements CommandLineRunner {

	final private CategoriaRepository categoriaRepository;
	
	public static void main(String[] args) {
		SpringApplication.run(WsMarketBackendApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		Categoria cat1 = new Categoria(null, "Informática");
		Categoria cat2 = new Categoria(null, "Escritório");

		this.categoriaRepository.saveAll(Arrays.asList(cat1, cat2));

	}
}
