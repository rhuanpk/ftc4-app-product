package org.example.product.core.applications.produto.usecases;


import org.example.product.core.applications.produto.repositories.ProdutoRepositoryInterface;

import java.util.UUID;

public class DeletarProduto {

    private final ProdutoRepositoryInterface produtoRepository;

    public DeletarProduto(ProdutoRepositoryInterface produtoRepository) {
        this.produtoRepository = produtoRepository;
    }

    public void execute(UUID id) {
        this.produtoRepository.deleteProdutoById(id);
    }

}
