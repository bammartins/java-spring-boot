package com.bmartins.modelagem.resources;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.bmartins.modelagem.domain.Produto;
import com.bmartins.modelagem.services.ProdutoService;

@RestController
@RequestMapping(value="/produtos")
public class ProdutoResource {

	@Autowired
	private ProdutoService produtoService;
	
	@RequestMapping(value = "/adicionarProduto", method = RequestMethod.POST)
	public ResponseEntity<List<Produto>> createProduct (@RequestBody List<Produto> p){
		produtoService.createProduct(p);
		return ResponseEntity.ok(p);
	}
	
	@RequestMapping(value = "/atualizar/{id}", method = RequestMethod.PATCH)
	public ResponseEntity<Produto> updateProduct (@PathVariable Integer id, @RequestBody Produto p){
		produtoService.updateProduct(id, p);
		return ResponseEntity.ok(p);
	}
	
	@RequestMapping(value = "/deletar/{id}", method = RequestMethod.DELETE)
	public ResponseEntity<?> deleteProduct (@PathVariable Integer id){
		produtoService.deleteProduct(id);
		return ResponseEntity.ok("Produto deletado com sucesso!");
	}
	
	@RequestMapping(method = RequestMethod.GET)
	public ResponseEntity<List<Produto>> searchProduct(){
		List<Produto> prodList = produtoService.searchAll();
		return ResponseEntity.ok(prodList);
	}
}
