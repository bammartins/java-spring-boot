package com.bmartins.modelagem.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.bmartins.modelagem.domain.Produto;

public interface ProdutoRepository extends JpaRepository<Produto, Integer>{

}
