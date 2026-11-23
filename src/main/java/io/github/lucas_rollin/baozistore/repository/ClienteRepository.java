package io.github.lucas_rollin.baozistore.repository;

import io.github.lucas_rollin.baozistore.model.Cliente;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ClienteRepository extends JpaRepository<Cliente, Long> {
}
