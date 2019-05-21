package com.bmartins.modelagem.resources;

import java.net.URI;
import java.util.List;
import java.util.stream.Collectors;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.bmartins.modelagem.domain.Cliente;
import com.bmartins.modelagem.dto.ClienteDTO;
import com.bmartins.modelagem.services.ClienteService;

@RestController
@RequestMapping(value="/clientes")
public class ClienteResource {
	
	@Autowired
	private ClienteService service;
	
	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	public ResponseEntity<?> findById (@PathVariable Integer id){
		Cliente cli = service.searchById(id);
		return ResponseEntity.ok(cli);
	}
	
	@RequestMapping(method = RequestMethod.GET)
	public ResponseEntity<?> list() {
		List<Cliente> obj = service.searchAll();
		List<ClienteDTO> listDto = obj.stream().map(
														item -> new ClienteDTO(item)
													 ).collect(Collectors.toList());
		return ResponseEntity.ok(listDto);
	}
	
	@RequestMapping(value = "/page", method = RequestMethod.GET)
	public ResponseEntity<Page<ClienteDTO>> listPagged(
			@RequestParam(value = "page", defaultValue = "0") Integer p, 
			@RequestParam(value = "lines", defaultValue = "24") Integer lpp, 
			@RequestParam(value = "orderBy", defaultValue = "nome") String orderBy, 
			@RequestParam(value = "direction", defaultValue = "ASC") String direction) {
		Page<Cliente> obj = service.findPerPage(p, lpp, orderBy, direction);
		Page<ClienteDTO> listDto = obj.map(
												item -> new ClienteDTO(item)
											);
		return ResponseEntity.ok(listDto);
	}
	
	@RequestMapping(method = RequestMethod.POST)
	public ResponseEntity<Void> create(@Valid @RequestBody ClienteDTO c){
		Cliente cat = service.fromDTO(c);
		service.create(cat);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest()
				  .path("/{id}").buildAndExpand(c.getId()).toUri();
		
		return ResponseEntity.created(uri).build();
		
	}
	
	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
	public ResponseEntity<?> delete(@PathVariable Integer id){
		service.delete(id);
		return ResponseEntity.ok(id);
	}
	
	@RequestMapping(value = "/{id}", method = RequestMethod.PUT)
	public ResponseEntity<Void> update(@PathVariable Integer id, @Valid @RequestBody ClienteDTO c){
		Cliente cli = service.fromDTO(c);
		cli.setId(id);
		service.update(cli);
		return ResponseEntity.noContent().build();
	}

}
