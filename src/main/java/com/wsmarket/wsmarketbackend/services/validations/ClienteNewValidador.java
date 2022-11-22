package com.wsmarket.wsmarketbackend.services.validations;


import java.util.ArrayList;
import java.util.List;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import org.springframework.beans.factory.annotation.Autowired;

import com.wsmarket.wsmarketbackend.domains.Cliente;
import com.wsmarket.wsmarketbackend.domains.enums.TipoCliente;
import com.wsmarket.wsmarketbackend.dtos.ClienteNewDTO;
import com.wsmarket.wsmarketbackend.repositories.ClienteRepository;
import com.wsmarket.wsmarketbackend.resources.exceptions.FieldMessage;
import com.wsmarket.wsmarketbackend.services.validations.utils.DocumentUtil;

public class ClienteNewValidador implements ConstraintValidator<ClienteNew, ClienteNewDTO> {
	@Autowired
	private ClienteRepository clienteRepository;
	
	@Override
	public void initialize(ClienteNew ann) {
	}

	@Override
	public boolean isValid(ClienteNewDTO clienteNewDto, ConstraintValidatorContext context) {
		List<FieldMessage> errorList = new ArrayList<FieldMessage>();

		if(
			clienteNewDto.getTipo().equals(TipoCliente.PESSOA_FISICA.getCodigo()) &&
			!DocumentUtil.isValidCPF(clienteNewDto.getCpfOuCnpj())
		) {
			errorList.add(new FieldMessage("cpfOuCnpj", "Invalid CPF"));
		}

		if(
			clienteNewDto.getTipo().equals(TipoCliente.PESSOA_JURIDICA.getCodigo()) &&
			!DocumentUtil.isValidCNPJ(clienteNewDto.getCpfOuCnpj())
		) {
			errorList.add(new FieldMessage("cpfOuCnpj", "Invalid CNPJ"));
		}

		Cliente cliente = this.clienteRepository.findByEmail(clienteNewDto.getEmail());

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
