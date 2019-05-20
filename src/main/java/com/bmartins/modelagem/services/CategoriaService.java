package com.bmartins.modelagem.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;

import com.bmartins.modelagem.domain.Categoria;
import com.bmartins.modelagem.dto.CategoriaDTO;
import com.bmartins.modelagem.repositories.CategoriaRepository;
import com.bmartins.modelagem.services.exception.DataIntegrityException;
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
	
	public void createCategory(Categoria data){
		repo.save(data);
	}
	
	public void deleteCategory (Integer id) {
		searchById(id);
		
		try {
			repo.deleteById(id);
		} catch (DataIntegrityViolationException e) {
			throw new DataIntegrityException("Não é possíviel excluir uma categoria que possui produtos");
		}
	}
	
	public void updateCategory (Categoria nCat){
		searchById(nCat.getId());
		repo.save(nCat);
	}
	
	public Page<Categoria> findPerPage(Integer p, Integer lpp, String orderBy, String direction){
		PageRequest pageRequest = PageRequest.of(p, lpp, Direction.valueOf(direction), orderBy);
		return repo.findAll(pageRequest);
	}
	
	public Categoria fromDTO(CategoriaDTO c) {
		return new Categoria(c.getId(), c.getNome());
	}
}
