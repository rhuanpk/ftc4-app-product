package org.example.product.core.applications.produto.usecases;

import org.example.product.application.driven.entities.produto.ProdutoEntity;
import org.example.product.core.applications.produto.repositories.ProdutoRepositoryInterface;
import org.example.product.core.domain.produto.enums.TipoProduto;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import java.math.BigDecimal;
import java.util.UUID;

@ActiveProfiles("test")
@SpringBootTest
class AtualizarProdutoTest {

    private AtualizarProduto atualizarProduto;

    @Mock
    private ProdutoRepositoryInterface produtoRepositoryInterface;

    private ProdutoEntity produtoEntity;

    @Test
    void whenExecuteUpdateProductThenUpdateProduct() {
        atualizarProduto.execute(produtoEntity.getId(), produtoEntity.getNome(), produtoEntity.getPreco(), produtoEntity.getTipoProduto());

        Mockito.verify(produtoRepositoryInterface, Mockito.times(1))
                .update(produtoEntity.getId(), produtoEntity.getNome(), produtoEntity.getPreco(), produtoEntity.getTipoProduto());
    }

    @BeforeEach
    void setUp() {

        MockitoAnnotations.openMocks(this);
        atualizarProduto = new AtualizarProduto(produtoRepositoryInterface);


        produtoEntity = new ProdutoEntity();
        UUID id = UUID.fromString("838b1e25-a271-4816-a16b-a585470d84ed");
        produtoEntity.setId(id);
        produtoEntity.setNome("Produto 1");
        produtoEntity.setPreco(BigDecimal.TEN);
        produtoEntity.setTipoProduto(TipoProduto.BEBIDA);

    }

}