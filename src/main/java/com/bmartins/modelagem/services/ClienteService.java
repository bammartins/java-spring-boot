package com.bmartins.modelagem.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.bmartins.modelagem.domain.Cidade;
import com.bmartins.modelagem.domain.Cliente;
import com.bmartins.modelagem.domain.Endereco;
import com.bmartins.modelagem.domain.enums.TipoCliente;
import com.bmartins.modelagem.dto.ClienteDTO;
import com.bmartins.modelagem.dto.ClienteNewDTO;
import com.bmartins.modelagem.repositories.CidadeRepository;
import com.bmartins.modelagem.repositories.ClienteRepository;
import com.bmartins.modelagem.repositories.EnderecoRepository;
import com.bmartins.modelagem.services.exception.DataIntegrityException;
import com.bmartins.modelagem.services.exception.ObjectNotFound;

@Service
public class ClienteService {
	
	@Autowired
	private ClienteRepository clienteRepository;
	
	@Autowired
	private CidadeRepository cidadeRepository;
	
	@Autowired
	private EnderecoRepository enderecoRepository;
	
	public Cliente searchById (Integer id){
		Optional<Cliente> client = clienteRepository.findById(id);
		return client.orElseThrow(() -> new ObjectNotFound("O cliente referente ao id " + id + " não foi encontrado" ));
	}
	
	public List<Cliente> searchAll(){
		List<Cliente> catList = clienteRepository.findAll();
		return catList;
	}
	
	@Transactional
	public Cliente create(Cliente data){
		data.setId(null);
		clienteRepository.save(data);
		enderecoRepository.saveAll(data.getEnderecos());
		
		return data;
	}
	
	public void delete (Integer id) {
		searchById(id);
		
		try {
			clienteRepository.deleteById(id);
		} catch (DataIntegrityViolationException e) {
			throw new DataIntegrityException("Não é possíviel excluir este cliente");
		}
	}
	
	public void update(Cliente nCli){
		Cliente cli = searchById(nCli.getId());
		updateData(cli, nCli);
		clienteRepository.save(cli);
	}
	
	public Page<Cliente> findPerPage(Integer p, Integer lpp, String orderBy, String direction){
		PageRequest pageRequest = PageRequest.of(p, lpp, Direction.valueOf(direction), orderBy);
		return clienteRepository.findAll(pageRequest);
	}
	
	public Cliente fromDTO(ClienteDTO c) {
		return new Cliente(c.getId(), c.getNome(), c.getEmail(), null);
	}
	
	
	public Cliente fromDTO(ClienteNewDTO c) {
		Optional<Cidade> cid = cidadeRepository.findById(c.getCidadeId());
		Cliente cli = new Cliente(null, c.getNome(), c.getEmail(), TipoCliente.toEnum(c.getTipo()));
		Endereco end = new Endereco(
									null, 
									c.getLogradouro(), 
									c.getNumero(), 
									c.getComplemento(), 
									c.getBairro(),
									c.getCep(), 
									cid.get(), 
									cli);
		cli.getEnderecos().add(end);
		cli.getTelefones().add(c.getTelefones().get(0));
		
		if (c.getTelefones().size() >= 2) {
			for (String item : c.getTelefones()) {
				if (cli.getTelefones().size() <= 2 ) {
					cli.getTelefones().add(item);
				} else {
					throw new DataIntegrityException("O usuário não pode ter mais do que 3 telefones");
				}
			}
		}
		
		return cli;
	}
	
	private void updateData (Cliente cliBD, Cliente tempCli) {
		cliBD.setNome(tempCli.getNome());
		cliBD.setEmail(tempCli.getEmail());
	}
}
