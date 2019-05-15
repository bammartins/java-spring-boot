package com.bmartins.modelagem.resources;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.bmartins.modelagem.domain.Categoria;
import com.bmartins.modelagem.services.CategoriaService;

// Camada que disponibiliza os endpoints no padrao REST
@RestController
@RequestMapping(value="/categorias")
public class CategoriaResource {
	
	@Autowired
	private CategoriaService service;
	
	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	public ResponseEntity<?> findById(@PathVariable Integer id) {
		Optional<Categoria> obj = service.searchById(id);
		return ResponseEntity.ok(obj);
	}
	
	@RequestMapping(method = RequestMethod.GET)
	public ResponseEntity<?> listCategory() {
		List<Categoria> obj = service.searchAll();
		
		return ResponseEntity.ok(obj);
	}
	
	@RequestMapping(value = "/addNew", method = RequestMethod.POST)
	public ResponseEntity<List<Categoria>> createCategory(@RequestBody List<Categoria> c){
		service.createCategory(c);
		return ResponseEntity.ok(c);
	}
	
	@RequestMapping(value = "/remove/{id}", method = RequestMethod.DELETE)
	public ResponseEntity<?> createCategory(@PathVariable Integer id){
		service.deleteCategory(id);
		return ResponseEntity.ok(id);
	}
	
	@RequestMapping(value = "/update/{id}", method = RequestMethod.PATCH)
	public ResponseEntity<?> updateCategory(@PathVariable Integer id, @RequestBody Categoria c){
		service.updateCategory(id, c);
		return ResponseEntity.ok(c);
	}
}
