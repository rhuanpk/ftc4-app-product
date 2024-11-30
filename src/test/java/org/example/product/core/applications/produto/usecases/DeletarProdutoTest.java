package org.example.product.core.applications.produto.usecases;

import org.example.product.core.applications.produto.repositories.ProdutoRepositoryInterface;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;

@ActiveProfiles("test")
@SpringBootTest
class DeletarProdutoTest {

    private DeletarProduto deletarProduto;

    @Mock
    private ProdutoRepositoryInterface produtoRepositoryInterface;


    @Test
    void whenExecuteDeleteProductThenDeleteProduct() {

        UUID id = UUID.fromString("838b1e25-a271-4816-a16b-a585470d84ed");
        deletarProduto.execute(id);

        Mockito.verify(produtoRepositoryInterface, Mockito.times(1)).deleteProdutoById(id);
    }


    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        deletarProduto = new DeletarProduto(produtoRepositoryInterface);
    }

}