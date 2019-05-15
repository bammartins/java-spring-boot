package com.bmartins.modelagem.repositories;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.bmartins.modelagem.domain.Categoria;


//Camada que disponibiliza as transacoes com bd
@Repository
public interface CategoriaRepository extends JpaRepository<Categoria, Integer>{

}
