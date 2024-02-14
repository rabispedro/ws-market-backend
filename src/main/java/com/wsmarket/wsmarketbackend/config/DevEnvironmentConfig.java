package com.wsmarket.wsmarketbackend.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import com.wsmarket.wsmarketbackend.services.DatabaseService;
import com.wsmarket.wsmarketbackend.services.MockEmailService;
import com.wsmarket.wsmarketbackend.services.interfaces.IDatabaseService;
import com.wsmarket.wsmarketbackend.services.interfaces.IEmailService;

@Configuration
@Profile("dev")
public class DevEnvironmentConfig {
	private final IDatabaseService _databaseService;

	public DevEnvironmentConfig(@Autowired IDatabaseService databaseService) {
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
		return new MockEmailService(null, null);
	}
}
