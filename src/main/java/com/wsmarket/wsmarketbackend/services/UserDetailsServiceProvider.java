package com.wsmarket.wsmarketbackend.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.wsmarket.wsmarketbackend.domains.Cliente;
import com.wsmarket.wsmarketbackend.repositories.ClienteRepository;
import com.wsmarket.wsmarketbackend.security.UserSpringSecurity;

@Service
public class UserDetailsServiceProvider implements UserDetailsService {
	@Autowired
	private ClienteRepository clienteRepository;

	@Override
	public UserDetails loadUserByUsername(
		String email
	) throws UsernameNotFoundException {
		Cliente cliente = this.clienteRepository.findByEmail(email);
		if(cliente == null) {
			throw new UsernameNotFoundException(email);
		}

		UserSpringSecurity userSpringSecurity = new UserSpringSecurity(
			cliente.getId(),
			cliente.getEmail(),
			cliente.getSenha(),
			cliente.getPerfis()
		);
		return userSpringSecurity;
	}
}
