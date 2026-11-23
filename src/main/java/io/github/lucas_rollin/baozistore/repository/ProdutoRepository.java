package io.github.lucas_rollin.baozistore.repository;

import io.github.lucas_rollin.baozistore.model.Produto;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProdutoRepository extends JpaRepository<Produto, Long> {
}
