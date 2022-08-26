package com.correa.workShop.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.correa.workShop.entities.Usuario;

public interface UsuarioRepository extends JpaRepository<Usuario, Long> {
	

}
