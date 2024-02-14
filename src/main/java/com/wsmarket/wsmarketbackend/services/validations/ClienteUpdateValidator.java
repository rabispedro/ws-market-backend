package com.wsmarket.wsmarketbackend.services.validations;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.HandlerMapping;

import com.wsmarket.wsmarketbackend.domains.Cliente;
import com.wsmarket.wsmarketbackend.dtos.ClienteDto;
import com.wsmarket.wsmarketbackend.repositories.ClienteRepository;
import com.wsmarket.wsmarketbackend.resources.exceptions.FieldMessage;

public class ClienteUpdateValidator implements ConstraintValidator<ClienteUpdate, ClienteDto> {
	private final HttpServletRequest _httpServletRequest;
	private final ClienteRepository _clienteRepository;

	public ClienteUpdateValidator(
		@Autowired HttpServletRequest httpServletRequest,
		@Autowired ClienteRepository clienteRepository
	) {
		_httpServletRequest = httpServletRequest;
		_clienteRepository = clienteRepository;
	}

	@Override
	public void initialize(ClienteUpdate clienteUpdate) throws UnsupportedOperationException {}

	@Override
	public boolean isValid(ClienteDto clienteDTO, ConstraintValidatorContext context) {
		List<FieldMessage> errorList = new ArrayList<>();

		Map<String, String> requestMap = (Map<String, String>) _httpServletRequest.getAttribute(HandlerMapping.URI_TEMPLATE_VARIABLES_ATTRIBUTE);
		Long urlId = Long.parseLong(requestMap.get("id"));

		Cliente cliente = _clienteRepository.findByEmail(clienteDTO.email());

		if(
			cliente != null &&
			!cliente.getId().equals(urlId)
		) {
			errorList.add(new FieldMessage("email", "Email already in use"));
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
