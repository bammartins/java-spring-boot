package com.bmartins.modelagem.repositories;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.bmartins.modelagem.domain.Cliente;


//Camada que disponibiliza as transacoes com bd
@Repository
public interface ClienteRepository extends JpaRepository<Cliente, Integer>{

}
