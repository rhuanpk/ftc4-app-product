package org.example.product.core.applications.produto.usecases;

import org.example.product.application.driven.entities.produto.ProdutoEntity;
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
import org.springframework.test.context.ActiveProfiles;

import java.math.BigDecimal;
import java.util.List;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;

@ActiveProfiles("test")
@SpringBootTest
class SalvarProdutoTest {

    private SalvarProduto salvarProduto;

    @Mock
    private ProdutoRepositoryInterface produtoRepositoryInterface;

    private ProdutoEntity produtoEntity;

    private Produto produto;


    @Test
    void whenExecuteGetProductThenGetProduct() {

        Mockito.when(produtoRepositoryInterface.saveProduto(Mockito.any(Produto.class))).thenReturn(produto);

        Produto result = salvarProduto.execute(produtoEntity.getNome(), produtoEntity.getPreco(), produtoEntity.getTipoProduto());

        Assertions.assertEquals(produtoEntity.getNome(), result.getNome());
        Assertions.assertNotNull(result.getId());
        }

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        salvarProduto = new SalvarProduto(produtoRepositoryInterface);

        produtoEntity = new ProdutoEntity();
        produtoEntity.setNome("Produto 1");
        produtoEntity.setPreco(BigDecimal.TEN);
        produtoEntity.setTipoProduto(TipoProduto.BEBIDA);

        produto = new Produto(UUID.randomUUID(),
                produtoEntity.getNome(),
                produtoEntity.getPreco(),
                produtoEntity.getTipoProduto());
    }

}