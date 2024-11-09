package org.example.product.core.applications.produto.usecases;


import org.example.product.core.applications.exception.EntityNotFoundException;
import org.example.product.core.applications.produto.repositories.ProdutoRepositoryInterface;
import org.example.product.core.domain.produto.Produto;
import org.example.product.core.domain.produto.enums.TipoProduto;

import java.util.List;

public class GetProduto {

    private final ProdutoRepositoryInterface produtoRepository;

    public GetProduto(ProdutoRepositoryInterface produtoRepository) {
        this.produtoRepository = produtoRepository;
    }

    public List<Produto> execute(TipoProduto tipoProduto) {
        List<Produto> produtos = this.produtoRepository.getProdutoByTipoProduto(tipoProduto);
        if (produtos.isEmpty()) {
            throw new EntityNotFoundException("Produto não encontrado");
        }
        return produtos;
    }
}
