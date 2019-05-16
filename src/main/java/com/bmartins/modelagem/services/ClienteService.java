package com.bmartins.modelagem.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bmartins.modelagem.domain.Cliente;
import com.bmartins.modelagem.repositories.ClienteRepository;
import com.bmartins.modelagem.services.exception.ObjectNotFound;

@Service
public class ClienteService {
	
	@Autowired
	private ClienteRepository clienteRepository;
	
	public Cliente searchById (Integer id){
		Optional<Cliente> client = clienteRepository.findById(id);
		return client.orElseThrow(() -> new ObjectNotFound("O cliente referente ao id " + id + " não foi encontrado" ));
	}
}
