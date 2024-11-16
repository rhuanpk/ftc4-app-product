package org.example.product.adapters.presenters;

import org.example.product.core.domain.produto.Produto;
import org.example.product.core.domain.produto.enums.TipoProduto;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
public class ProdutoPresenterTest {

    @Test
    void testToObject() {
        Produto produto = new Produto(UUID.randomUUID(), "Produto A", BigDecimal.TEN, TipoProduto.BEBIDA);
        Object result = ProdutoPresenter.toObject(produto);

        assertNotNull(result);
        assertInstanceOf(Map.class, result);

        Map<String, Object> presenter = (Map<String, Object>) result;
        assertEquals(produto.getId(), presenter.get("id"));
        assertEquals("Produto A", presenter.get("nome"));
        assertEquals(BigDecimal.TEN, presenter.get("preco"));
        assertEquals(TipoProduto.BEBIDA, presenter.get("tipo_produto"));
    }

    @Test
    void testToList() {
        List<Produto> produtos = new ArrayList<>();
        produtos.add(new Produto(UUID.randomUUID(), "Produto A", BigDecimal.TEN, TipoProduto.BEBIDA));
        produtos.add(new Produto(UUID.randomUUID(), "Produto B", BigDecimal.TEN, TipoProduto.BEBIDA));

        List<Object> result = ProdutoPresenter.toList(produtos);

        assertNotNull(result);
        assertEquals(2, result.size());
    }
}