package com.correa.workShop.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.correa.workShop.entities.Pedido;
import com.correa.workShop.repositories.PedidoRepository;

@Service
public class PedidoService {
	
	@Autowired
	private PedidoRepository repository;

	public List<Pedido> findAll() {
		return repository.findAll();
	}

	public Pedido findById(Long id) {
		Optional<Pedido> obj = repository.findById(id);
		return obj.get();
	}

}
