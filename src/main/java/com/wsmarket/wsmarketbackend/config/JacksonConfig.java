package com.wsmarket.wsmarketbackend.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.converter.json.Jackson2ObjectMapperBuilder;
import org.springframework.lang.NonNull;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.wsmarket.wsmarketbackend.domains.PagamentoComBoleto;
import com.wsmarket.wsmarketbackend.domains.PagamentoComCartao;

@Configuration
public class JacksonConfig {
	//	Helper: https://stackoverflow.com/questions/41452598/overcome-can-not-construct-instance-ofinterfaceclass-without-hinting-the-pare

	@Bean
	public Jackson2ObjectMapperBuilder objectMapperBuilder() {
		return new Jackson2ObjectMapperBuilder() {
			@Override
			public void configure(@NonNull ObjectMapper objectMapper) {
				objectMapper.registerSubtypes(PagamentoComBoleto.class);
				objectMapper.registerSubtypes(PagamentoComCartao.class);

				super.configure(objectMapper);
			}
		};
	}
}
