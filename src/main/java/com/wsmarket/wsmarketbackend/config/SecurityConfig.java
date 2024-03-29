package com.wsmarket.wsmarketbackend.config;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import com.wsmarket.wsmarketbackend.security.JWTAuthenticationFilter;
import com.wsmarket.wsmarketbackend.security.utils.JWTUtil;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {
	private Environment _environment;
	private UserDetailsService _userDetailsService;
	private JWTUtil _jwtUtil;

	public SecurityConfig(
		@Autowired Environment environment,
		@Autowired UserDetailsService userDetailsService,
		@Autowired JWTUtil jwtUtil) {
		_environment = environment;
		_userDetailsService = userDetailsService;
		_jwtUtil = jwtUtil;
	}
	
	private static final String[] PUBLIC_MATCHERS = {
		"/swagger/**"
	};

	private static final String[] PUBLIC_MATCHERS_GET = {
		"/produtos/**",
		"/categorias/**"
	};

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		if(Arrays.asList(_environment.getActiveProfiles()).contains("dev")) {
			http
				.headers()
				.frameOptions()
				.disable();
		}

		http
			.cors()
			.and()
			.csrf()
			.disable();
		
		http.authorizeRequests()
			.antMatchers(HttpMethod.GET, PUBLIC_MATCHERS_GET)
			.permitAll()
			.antMatchers(PUBLIC_MATCHERS)
			.permitAll()
			.anyRequest()
			.authenticated();

		http
			.addFilter(new JWTAuthenticationFilter(
				authenticationManager(),
				_jwtUtil));

		http
			.sessionManagement()
			.sessionCreationPolicy(SessionCreationPolicy.STATELESS);
	}

	@Override
	protected void configure(
		AuthenticationManagerBuilder authenticationBuilder
	) throws Exception {
		authenticationBuilder
			.userDetailsService(_userDetailsService)
			.passwordEncoder(bCryptPasswordEncoder());
	}

	@Bean
	public CorsConfigurationSource corsConfigurationSource() {
		final UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
		source.registerCorsConfiguration("/**", new CorsConfiguration().applyPermitDefaultValues());

		return source;
	}

	@Bean
	public BCryptPasswordEncoder bCryptPasswordEncoder() {
		return new BCryptPasswordEncoder();
	}
}
