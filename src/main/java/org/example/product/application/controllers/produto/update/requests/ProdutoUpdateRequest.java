package org.example.product.application.controllers.produto.update.requests;



import org.example.product.core.domain.produto.enums.TipoProduto;

import java.math.BigDecimal;

public record ProdutoUpdateRequest(String nome, BigDecimal preco, TipoProduto tipoProduto) {
}
