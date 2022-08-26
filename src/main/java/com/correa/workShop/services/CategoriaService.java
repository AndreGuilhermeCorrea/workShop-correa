package com.correa.workShop.services;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.correa.workShop.entities.Categoria;
import com.correa.workShop.repositories.CategoriaRepository;

@Service
public class CategoriaService {

	private CategoriaRepository repository;

	public List<Categoria> findAll() {
		return repository.findAll();
	}

	public Categoria findById(Long id) {
		Optional<Categoria> obj = repository.findById(id);
		return obj.get();
	}

}
