package com.bmartins.modelagem.services.validation;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import org.springframework.beans.factory.annotation.Autowired;

import com.bmartins.modelagem.domain.Cliente;
import com.bmartins.modelagem.domain.enums.TipoCliente;
import com.bmartins.modelagem.dto.ClienteNewDTO;
import com.bmartins.modelagem.repositories.ClienteRepository;
import com.bmartins.modelagem.resources.exception.FieldMessage;
import com.bmartins.modelagem.services.validation.utils.BR;

public class ClienteInsertValidator implements ConstraintValidator<ClienteInsert, ClienteNewDTO> {
	
	@Autowired
	private ClienteRepository clienteRepository;
	
	@Override
	public void initialize(ClienteInsert ann) {
	}

	@Override
	public boolean isValid(ClienteNewDTO cli, ConstraintValidatorContext context) {
		List<FieldMessage> list = new ArrayList<>();
		
		Cliente cliByEmail = clienteRepository.findByEmail(cli.getEmail());
		
		if (cliByEmail != null && cliByEmail.getEmail() != null) {
			list.add(new FieldMessage("email", "Email indisponível"));
		}
		
		if (cli.getTipo().equals(TipoCliente.PESSOAFISICA.getId()) && !BR.isValidSsn(cli.getDocumento())) {
			list.add(new FieldMessage("documento", "CPF inválido"));
		}
		
		if (cli.getTipo().equals(TipoCliente.PESSOAJURIDICA.getId()) && !BR.isValidTin(cli.getDocumento())) {
			list.add(new FieldMessage("documento", "CNPJ inválido"));
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