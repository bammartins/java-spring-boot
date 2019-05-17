package com.bmartins.modelagem.repositories;


import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.bmartins.modelagem.domain.ItemPedido;


//Camada que disponibiliza as transacoes com bd
@Repository
public interface ItemPedidoRepository extends JpaRepository<ItemPedido, Integer>{
	
//	List<ItemPedido> findBy
}
