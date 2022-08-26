package com.correa.workShop.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.correa.workShop.entities.Produto;

public interface ProdutoRepository extends JpaRepository<Produto, Long>{

}
