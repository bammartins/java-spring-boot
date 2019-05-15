package com.bmartins.modelagem.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bmartins.modelagem.domain.Categoria;
import com.bmartins.modelagem.repositories.CategoriaRepository;
import com.bmartins.modelagem.services.exception.ObjectNotFound;

/*
 * Camada que utiliza operacoes do Repository para disponibilizar 
 * para os endpoints as transacoes com o banco
 */

@Service
public class CategoriaService {
	/*
	 * Dependencias declaradas dentro da classe com o
	 * @Autowired sera automaticamente instaciado pelo
	 * Spring 
	 */ 
	@Autowired
	private CategoriaRepository repo;
	
	public Categoria searchById(Integer id) {
		Optional<Categoria> category = repo.findById(id);
		return category.orElseThrow(
				() -> 
				new ObjectNotFound("Categoria não encontrada! Id fornecido: "+ id)
				);
	}
	
	public List<Categoria> searchAll(){
		List<Categoria> catList = repo.findAll();
		return catList;
	}
	
	public void createCategory(List<Categoria> data){
		try {
			repo.saveAll(data);
		} catch (Exception e) {
			// TODO: handle exception
			System.out.println(e);
		}
	}
	
	public void deleteCategory (Integer id) {
		Categoria cat = searchById(id);
		
		if (cat == null) {
			throw new ObjectNotFound("Categoria não encontrada!");
		} else {
			repo.deleteById(id);
		}
	}
	
	public void updateCategory (Integer id, Categoria nCat){
		Optional<Categoria> alteredCategory = repo.findById(id);
		nCat.setId(alteredCategory.get().getId());
		repo.save(nCat);
	}
}
