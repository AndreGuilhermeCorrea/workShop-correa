package com.correa.workShop.resources;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.correa.workShop.entities.Pedido;
import com.correa.workShop.services.PedidoService;

//controlador rest
@RestController
@RequestMapping(value = "/pedidos")
public class PedidoResource {
	
	//injecao de dependencia para o service
	@Autowired 
	private PedidoService service;

	@GetMapping
	public ResponseEntity<List<Pedido>> findAll() {
		List<Pedido> list = service.findAll();
		return ResponseEntity.ok().body(list);
	}

	//entrepoint para buscar o usuário por id
	@GetMapping(value = "/{id}")
	public ResponseEntity<Pedido> findById(@PathVariable Long id) {
		Pedido obj = service.findById(id);
		return ResponseEntity.ok().body(obj);
	}
}


