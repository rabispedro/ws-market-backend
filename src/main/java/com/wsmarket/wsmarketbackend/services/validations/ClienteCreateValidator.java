package com.wsmarket.wsmarketbackend.services.validations;


import java.util.ArrayList;
import java.util.List;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import org.springframework.beans.factory.annotation.Autowired;

import com.wsmarket.wsmarketbackend.domains.Cliente;
import com.wsmarket.wsmarketbackend.domains.enums.TipoCliente;
import com.wsmarket.wsmarketbackend.dtos.ClienteNewDto;
import com.wsmarket.wsmarketbackend.repositories.ClienteRepository;
import com.wsmarket.wsmarketbackend.resources.exceptions.FieldMessage;
import com.wsmarket.wsmarketbackend.services.validations.utils.DocumentUtil;

public class ClienteCreateValidator implements ConstraintValidator<ClienteCreate, ClienteNewDto> {
	private final ClienteRepository _clienteRepository;
	
	public ClienteCreateValidator(
		@Autowired ClienteRepository clienteRepository
	) {
		_clienteRepository = clienteRepository;
	}

	@Override
	public void initialize(ClienteCreate ann) {
	}

	@Override
	public boolean isValid(ClienteNewDto clienteNewDto, ConstraintValidatorContext context) {
		List<FieldMessage> errorList = new ArrayList<>();

		if(
			clienteNewDto.tipo().equals(TipoCliente.PESSOA_FISICA.getCodigo()) &&
			!DocumentUtil.isValidCPF(clienteNewDto.cpfOuCnpj())
		) {
			errorList.add(new FieldMessage("cpfOuCnpj", "Invalid CPF"));
		}

		if(
			clienteNewDto.tipo().equals(TipoCliente.PESSOA_JURIDICA.getCodigo()) &&
			!DocumentUtil.isValidCNPJ(clienteNewDto.cpfOuCnpj())
		) {
			errorList.add(new FieldMessage("cpfOuCnpj", "Invalid CNPJ"));
		}

		Cliente cliente = _clienteRepository.findByEmail(clienteNewDto.email());

		if(cliente != null) {
			errorList.add(new FieldMessage("email", "Email already exists"));
		}

		for(FieldMessage error : errorList) {
			context.disableDefaultConstraintViolation();
			context.buildConstraintViolationWithTemplate(error.getMessage())
				.addPropertyNode(error.getFieldName())
				.addConstraintViolation();
		}

		return errorList.isEmpty();
	}
}
