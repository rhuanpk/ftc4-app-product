package org.example.product.core.domain.produto;

import org.example.product.core.applications.exception.RegraDeNegocioException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.*;

@ActiveProfiles("test")
@SpringBootTest
class ProdutoTest {

    @Test
    void whenProductNameIsNullThenReturnThrow() {
        Produto produto = new Produto();
        assertThrows(RegraDeNegocioException.class, () -> produto.setNome(null));
    }

    @Test
    void whenProductNameIsBlankThenReturnThrow() {
        Produto produto = new Produto();
        assertThrows(RegraDeNegocioException.class, () -> produto.setNome(""));
    }

    @Test
    void whenProductTypeIsNullThenReturnThrow() {
        Produto produto = new Produto();
        assertThrows(RegraDeNegocioException.class, () -> produto.setTipoProduto(null));
    }

    @Test
    void whenPriceIsNullThenReturnThrow() {
        Produto produto = new Produto();
        assertThrows(RegraDeNegocioException.class, () -> produto.setPreco(null));
    }

    @Test
    void whenPriceIsNegativeThenReturnThrow() {
        Produto produto = new Produto();
        assertThrows(RegraDeNegocioException.class, () -> produto.setPreco(BigDecimal.valueOf(-1)));
    }

}