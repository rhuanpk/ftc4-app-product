package org.example.product.application.driven.repositories.produto;

import org.example.product.application.driven.entities.produto.ProdutoEntity;
import org.example.product.core.domain.produto.enums.TipoProduto;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface ProdutoRepository extends JpaRepository<ProdutoEntity, UUID> {

    List<ProdutoEntity> findByTipoProduto(TipoProduto tipoProduto);

    Optional<ProdutoEntity> findByNome(String produto);

    Optional<ProdutoEntity> findById(UUID id);

}
