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

import com.bmartins.modelagem.domain.Categoria;
import com.bmartins.modelagem.dto.CategoriaDTO;
import com.bmartins.modelagem.services.CategoriaService;

// Camada que disponibiliza os endpoints no padrao REST
@RestController
@RequestMapping(value="/categorias")
public class CategoriaResource {
	
	@Autowired
	private CategoriaService service;
	
	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	public ResponseEntity<?> findById(@PathVariable Integer id) {
		Categoria obj = service.searchById(id);
		return ResponseEntity.ok(obj);
	}
	
	@RequestMapping(method = RequestMethod.GET)
	public ResponseEntity<?> listCategory() {
		List<Categoria> obj = service.searchAll();
		List<CategoriaDTO> listDto = obj.stream().map(
														item -> new CategoriaDTO(item)
													 ).collect(Collectors.toList());
		return ResponseEntity.ok(listDto);
	}
	
	@RequestMapping(value = "/page", method = RequestMethod.GET)
	public ResponseEntity<Page<CategoriaDTO>> listPagged(
			@RequestParam(value = "page", defaultValue = "0") Integer p, 
			@RequestParam(value = "lines", defaultValue = "24") Integer lpp, 
			@RequestParam(value = "orderBy", defaultValue = "nome") String orderBy, 
			@RequestParam(value = "direction", defaultValue = "ASC") String direction) {
		Page<Categoria> obj = service.findPerPage(p, lpp, orderBy, direction);
		Page<CategoriaDTO> listDto = obj.map(
												item -> new CategoriaDTO(item)
											);
		return ResponseEntity.ok(listDto);
	}
	
	@RequestMapping(method = RequestMethod.POST)
	public ResponseEntity<Void> createCategory(@Valid @RequestBody CategoriaDTO c){
		Categoria cat = service.fromDTO(c);
		service.createCategory(cat);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest()
				  .path("/{id}").buildAndExpand(c.getId()).toUri();
		
		return ResponseEntity.created(uri).build();
		
	}
	
	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
	public ResponseEntity<?> createCategory(@PathVariable Integer id){
		service.deleteCategory(id);
		return ResponseEntity.ok(id);
	}
	
	@RequestMapping(value = "/{id}", method = RequestMethod.PUT)
	public ResponseEntity<Void> updateCategory(@PathVariable Integer id, @Valid @RequestBody CategoriaDTO c){
		Categoria cat = service.fromDTO(c);
		cat.setId(id);
		service.updateCategory(cat);
		return ResponseEntity.noContent().build();
	}
}
