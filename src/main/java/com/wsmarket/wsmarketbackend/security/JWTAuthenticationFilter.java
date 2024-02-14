package com.wsmarket.wsmarketbackend.security;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.wsmarket.wsmarketbackend.dtos.CredenciaisDto;
import com.wsmarket.wsmarketbackend.security.utils.JWTUtil;

public class JWTAuthenticationFilter extends UsernamePasswordAuthenticationFilter {
	private AuthenticationManager authenticationManager;
	private JWTUtil jwtUtil;

	public JWTAuthenticationFilter(
		AuthenticationManager authenticationManager,
		JWTUtil jwtUtil
	) {
		setAuthenticationFailureHandler(new JWTAuthenticationFailureHandler());
		this.authenticationManager = authenticationManager;
		this.jwtUtil = jwtUtil;
	}

	@Override
	public Authentication attemptAuthentication(
		HttpServletRequest request,
		HttpServletResponse response
	) throws AuthenticationException {
		try {
			CredenciaisDto credentialsDto = new ObjectMapper()
				.readValue(request.getInputStream(), CredenciaisDto.class);

			UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(
					credentialsDto.email(),
					credentialsDto.senha(),
					new ArrayList<>()
				);

			return this.authenticationManager.authenticate(authenticationToken);
		} catch(IOException exception) {
			throw new RuntimeException(exception);
		}
	}

	@Override
	protected void successfulAuthentication(
		HttpServletRequest request,
		HttpServletResponse response,
		FilterChain filterChain,
		Authentication authentication
	) throws IOException, ServletException {
		String email = ((UserSpringSecurity) authentication.getPrincipal()).getUsername();
		String token = this.jwtUtil.generateToken(email);

		response.addHeader(HttpHeaders.AUTHORIZATION, "Bearer " + token);
	}

	private class JWTAuthenticationFailureHandler implements AuthenticationFailureHandler {
		@Override
		public void onAuthenticationFailure(
			HttpServletRequest request,
			HttpServletResponse response,
			AuthenticationException exception
		) throws IOException, ServletException {
			response.setStatus(HttpStatus.UNAUTHORIZED.value());
			response.setContentType("application/json");
			response.getWriter().append(this.json());
		}
		
		private String json() {
			long date = new Date().getTime();

			return (
				"{\n" +
					"\t\"timestamp\": " + date + ",\n" +
					"\t\"status\": " + HttpStatus.UNAUTHORIZED.value() + ",\n" +
					"\t\"error\": \"Não autorizado\",\n" +
					"\t\"message\": \"Email ou senha inválidos\",\n" +
					"\t\"path\": \"/login\"" +
				"}"
			);
		}
	}
}
