package org.example.product.application.controllers.produto.create.requests;


import org.example.product.core.domain.produto.enums.TipoProduto;

import java.math.BigDecimal;

public record ProdutoCreateRequest(String nome, BigDecimal preco, TipoProduto tipoProduto) {
}
