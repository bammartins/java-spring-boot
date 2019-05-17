package com.bmartins.modelagem.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bmartins.modelagem.domain.Pedido;
import com.bmartins.modelagem.repositories.ItemPedidoRepository;
import com.bmartins.modelagem.repositories.PedidoRepository;
import com.bmartins.modelagem.services.exception.ObjectNotFound;

@Service
public class PedidoService {
	
	@Autowired
	private PedidoRepository pedidoRepository;
	
	@SuppressWarnings("unused")
	@Autowired
	private ItemPedidoRepository itemPedidoRepository;
	
	public Pedido findById(Integer id) {
		Optional<Pedido> pedido = pedidoRepository.findById(id);
		
		return pedido.orElseThrow(() -> new ObjectNotFound("Pedido referente ao ID "+id+ " não foi localizado"));
	}
	

}
