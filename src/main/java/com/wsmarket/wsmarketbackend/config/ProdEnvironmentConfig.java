package com.wsmarket.wsmarketbackend.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import com.wsmarket.wsmarketbackend.services.SmtpEmailService;
import com.wsmarket.wsmarketbackend.services.interfaces.IDatabaseService;
import com.wsmarket.wsmarketbackend.services.interfaces.IEmailService;

@Configuration
@Profile("prd")
public class ProdEnvironmentConfig {
	private final IDatabaseService _databaseService;
	
	public ProdEnvironmentConfig(@Autowired IDatabaseService databaseService) {
		_databaseService = databaseService;
	}

	@Value("${spring.jpa.hibernate.ddl-auto}")
	private String strategy;

	@Bean
	public boolean instantiateDatabase() throws Exception {
		if(!strategy.equals("create")) {
			return false;
		}
		
		_databaseService.instantiateTestDatabase();
		
		return true;
	}

	@Bean
	public IEmailService emailService() {
		return new SmtpEmailService(null, null);
	}
}
