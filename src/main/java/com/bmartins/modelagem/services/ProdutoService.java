package com.bmartins.modelagem.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bmartins.modelagem.domain.Produto;
import com.bmartins.modelagem.repositories.ProdutoRepository;

@Service
public class ProdutoService {
	
	@Autowired
	private ProdutoRepository repo;
	
	public void createProduct (List<Produto> p) {
		try {
			repo.saveAll(p);
		} catch (Exception e) {
			// TODO: handle exception
			System.out.println(e);
		}
	}
	
	public void updateProduct (Integer id, Produto p) {
		Optional<Produto> alteredProduct = repo.findById(id);
		p.setId(alteredProduct.get().getId());
		repo.save(p);
	}
	
	public void deleteProduct (Integer id) {
		repo.deleteById(id);
	}
	
	public List<Produto> searchAll (){
		List<Produto> prodList = repo.findAll();
		return prodList;
	}
	
	public Optional<Produto> searchById (Integer id){
		Optional<Produto> prod = repo.findById(id);
		return prod;
	}
}
