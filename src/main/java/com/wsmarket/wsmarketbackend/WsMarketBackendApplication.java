package com.wsmarket.wsmarketbackend;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class WsMarketBackendApplication implements CommandLineRunner {
	public static void main(String[] args) {
		SpringApplication.run(WsMarketBackendApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
	}
}
