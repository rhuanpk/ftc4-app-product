package org.example.product.core.applications.produto.usecases;



import org.example.product.core.applications.produto.repositories.ProdutoRepositoryInterface;
import org.example.product.core.domain.produto.enums.TipoProduto;

import java.math.BigDecimal;
import java.util.UUID;

public class AtualizarProduto {

    private final ProdutoRepositoryInterface produtoRepository;

    public AtualizarProduto(ProdutoRepositoryInterface produtoRepository) {
        this.produtoRepository = produtoRepository;
    }

    public void execute(UUID id, String nome, BigDecimal preco, TipoProduto tipoProduto) {
        this.produtoRepository.update(id, nome, preco, tipoProduto);
    }

}
