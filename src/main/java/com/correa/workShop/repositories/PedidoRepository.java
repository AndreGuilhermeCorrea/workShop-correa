package com.correa.workShop.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.correa.workShop.entities.Pedido;

public interface PedidoRepository extends JpaRepository<Pedido, Long> {

}
