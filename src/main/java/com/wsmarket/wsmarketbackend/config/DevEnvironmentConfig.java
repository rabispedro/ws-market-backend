package com.wsmarket.wsmarketbackend.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import com.wsmarket.wsmarketbackend.services.DatabaseService;
import com.wsmarket.wsmarketbackend.services.MockEmailService;
import com.wsmarket.wsmarketbackend.services.interfaces.EmailService;

@Configuration
@Profile("dev")
public class DevEnvironmentConfig {
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

	@Bean
	public EmailService emailService() {
		return new MockEmailService();
	}
}
