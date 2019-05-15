package com.bmartins.modelagem.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.bmartins.modelagem.domain.Cidade;

public interface CidadeRepository extends JpaRepository<Cidade, Integer>{

}
