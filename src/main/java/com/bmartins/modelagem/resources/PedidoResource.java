package com.bmartins.modelagem.resources;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.bmartins.modelagem.domain.Pedido;
import com.bmartins.modelagem.services.PedidoService;

@RestController
@RequestMapping(value = "/pedidos")
public class PedidoResource {
	
	@Autowired
	private PedidoService pedidoService;
	
	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	public ResponseEntity<?> findById (@PathVariable Integer id){
		Pedido pedido = pedidoService.findById(id);
		
		return ResponseEntity.ok(pedido);
	}

}