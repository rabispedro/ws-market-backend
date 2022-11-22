package com.wsmarket.wsmarketbackend.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import com.wsmarket.wsmarketbackend.services.DatabaseService;

@Configuration
@Profile("prd")
public class ProdEnvironmentConfig {
	@Autowired
	private DatabaseService databaseService;
	
	@Value("${spring.jpa.hibernate.ddl-auto}")
	private String strategy;
	
	@Bean
	public boolean instantiateDatabase() throws Exception {
		if(!strategy.equals("create")) {
			return false;
		}
		
		this.databaseService.instantiateTestDatabase();
		
		return true;
	}
}
