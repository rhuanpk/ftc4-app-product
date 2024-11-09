package org.example.product.adapters.controllers;

import org.example.product.adapters.presenters.ProdutoPresenter;
import org.example.product.core.applications.produto.repositories.ProdutoRepositoryInterface;
import org.example.product.core.applications.produto.usecases.AtualizarProduto;
import org.example.product.core.applications.produto.usecases.DeletarProduto;
import org.example.product.core.applications.produto.usecases.GetProduto;
import org.example.product.core.applications.produto.usecases.SalvarProduto;
import org.example.product.core.domain.produto.Produto;
import org.example.product.core.domain.produto.enums.TipoProduto;

import java.math.BigDecimal;
import java.util.List;
import java.util.UUID;

public class ProdutoController {

    private final ProdutoRepositoryInterface produtoRepository;

    public ProdutoController(ProdutoRepositoryInterface produtoRepository) {
        this.produtoRepository = produtoRepository;
    }

    public Object salvar(String nome, BigDecimal preco, TipoProduto tipoProduto) {
        SalvarProduto salvarProduto = new SalvarProduto(this.produtoRepository);
        Produto produto = salvarProduto.execute(nome, preco, tipoProduto);
        return ProdutoPresenter.toObject(produto);
    }

    public List<Object> findByTipoProduto(TipoProduto tipoProduto) {
        GetProduto getProduto = new GetProduto(this.produtoRepository);
        List<Produto> produtos = getProduto.execute(tipoProduto);
        return ProdutoPresenter.toList(produtos);
    }

    public void deletar(UUID id) {
        DeletarProduto deletarProduto = new DeletarProduto(this.produtoRepository);
        deletarProduto.execute(id);
    }

    public void atualizar(UUID id, String nome, BigDecimal preco, TipoProduto tipoProduto) {
        AtualizarProduto atualizarProduto = new AtualizarProduto(this.produtoRepository);
        atualizarProduto.execute(id, nome, preco, tipoProduto);
    }

}
