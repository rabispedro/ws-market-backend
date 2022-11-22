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
import com.wsmarket.wsmarketbackend.dtos.ClienteDTO;
import com.wsmarket.wsmarketbackend.repositories.ClienteRepository;
import com.wsmarket.wsmarketbackend.resources.exceptions.FieldMessage;

public class ClienteUpdateValidator implements ConstraintValidator<ClienteUpdate, ClienteDTO> {
	@Autowired
	private HttpServletRequest httpServletRequest;
	
	@Autowired
	private ClienteRepository clienteRepository;

	@Override
	public void initialize(ClienteUpdate clienteUpdate) {
	}

	@Override
	public boolean isValid(ClienteDTO clienteDTO, ConstraintValidatorContext context) {
		List<FieldMessage> errorList = new ArrayList<FieldMessage>();

		Map<String, String> requestMap = (Map<String, String>) this.httpServletRequest.getAttribute(HandlerMapping.URI_TEMPLATE_VARIABLES_ATTRIBUTE);
		Long urlId = Long.parseLong(requestMap.get("id"));

		Cliente cliente = this.clienteRepository.findByEmail(clienteDTO.getEmail());

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
