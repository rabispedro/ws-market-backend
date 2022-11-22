package com.wsmarket.wsmarketbackend.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import com.wsmarket.wsmarketbackend.services.DatabaseService;

@Configuration
@Profile("dev")
public class DevEnvironmentConfig {
	@Autowired
	private DatabaseService databaseService;
	
	@Bean
	public boolean instantiateDatabase() throws Exception {
		this.databaseService.instantiateTestDatabase();

		return true;
	}
}
