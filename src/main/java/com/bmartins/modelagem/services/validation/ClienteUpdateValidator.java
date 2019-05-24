package com.bmartins.modelagem.services.validation;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.HandlerMapping;

import com.bmartins.modelagem.domain.Cliente;
import com.bmartins.modelagem.dto.ClienteDTO;
import com.bmartins.modelagem.repositories.ClienteRepository;
import com.bmartins.modelagem.resources.exception.FieldMessage;

public class ClienteUpdateValidator implements ConstraintValidator<ClienteUpdate, ClienteDTO> {
	
	@Autowired
	private HttpServletRequest request;
	
	@Autowired
	private ClienteRepository clienteRepository;
	
	@Override
	public void initialize(ClienteUpdate ann) {
	}

	@Override
	public boolean isValid(ClienteDTO cli, ConstraintValidatorContext context) {
		List<FieldMessage> list = new ArrayList<>();
		
		@SuppressWarnings("unchecked")
		Map<String, String> map = (Map<String, String>) request.getAttribute(HandlerMapping.URI_TEMPLATE_VARIABLES_ATTRIBUTE);
		
		Integer id = Integer.parseInt(map.get("id"));
		Cliente cliByEmail = clienteRepository.findByEmail(cli.getEmail());
//		Optional<Cliente> cliById = clienteRepository.findById(cliByEmail.getId());
		
		if (cliByEmail != null && !cliByEmail.getId().equals(id)) {
			list.add(new FieldMessage("email", "Este usuário já está cadastrado em nossa base."));
		}
		
		// inclua os testes aqui, inserindo erros na lista
		for (FieldMessage e : list) {
			context.disableDefaultConstraintViolation();
			context.buildConstraintViolationWithTemplate(e.getMessage()).addPropertyNode(e.getFieldName())
					.addConstraintViolation();
		}
		return list.isEmpty();
	}
}