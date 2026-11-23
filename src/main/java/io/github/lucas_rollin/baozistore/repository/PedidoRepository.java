package io.github.lucas_rollin.baozistore.repository;

import io.github.lucas_rollin.baozistore.model.Pedido;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PedidoRepository extends JpaRepository<Pedido, Long> {
}
