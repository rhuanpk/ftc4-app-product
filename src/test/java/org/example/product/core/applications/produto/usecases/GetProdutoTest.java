package org.example.product.core.applications.produto.usecases;

import org.example.product.core.applications.exception.EntityNotFoundException;
import org.example.product.core.applications.produto.repositories.ProdutoRepositoryInterface;
import org.example.product.core.domain.produto.Produto;
import org.example.product.core.domain.produto.enums.TipoProduto;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class GetProdutoTest {

    private GetProduto getProduto;

    @Mock
    private ProdutoRepositoryInterface produtoRepositoryInterface;

    private List<Produto> produtos;


    @Test
    void whenExecuteGetProductThenGetProduct() {

        Mockito.when(produtoRepositoryInterface.getProdutoByTipoProduto(TipoProduto.BEBIDA)).thenReturn(produtos);

        List<Produto> result = getProduto.execute(TipoProduto.BEBIDA);

        Assertions.assertEquals(1, result.size());
        Assertions.assertEquals(result.getFirst().getId(), UUID.fromString("838b1e25-a271-4816-a16b-a585470d84ed"));
    }


    @Test
    void whenExecuteGetProductThenReturnNotFound() {

        Assertions.assertThrows(EntityNotFoundException.class, () -> {
            getProduto.execute(TipoProduto.BEBIDA);
        });
    }


    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        getProduto = new GetProduto(produtoRepositoryInterface);

        produtos = new ArrayList<>();

        Produto produto = new Produto();
        produto.setId(UUID.fromString("838b1e25-a271-4816-a16b-a585470d84ed"));
        produto.setNome("Produto 1");
        produto.setPreco(BigDecimal.TEN);
        produto.setTipoProduto(TipoProduto.BEBIDA);
        produtos.add(produto);
    }

}