package com.bmartins.modelagem.services.validation;

import java.util.ArrayList;
import java.util.List;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import com.bmartins.modelagem.domain.enums.TipoCliente;
import com.bmartins.modelagem.dto.ClienteNewDTO;
import com.bmartins.modelagem.resources.exception.FieldMessage;
import com.bmartins.modelagem.services.validation.utils.BR;

public class ClienteInsertValidator implements ConstraintValidator<ClienteInsert, ClienteNewDTO> {
	@Override
	public void initialize(ClienteInsert ann) {
	}

	@Override
	public boolean isValid(ClienteNewDTO cli, ConstraintValidatorContext context) {
		List<FieldMessage> list = new ArrayList<>();
		
		
		
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