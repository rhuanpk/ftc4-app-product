package org.example.product.adapters.gateways;

import org.example.product.application.driven.entities.produto.ProdutoEntity;
import org.example.product.application.driven.repositories.produto.ProdutoRepository;
import org.example.product.core.applications.exception.EntityNotFoundException;
import org.example.product.core.domain.produto.Produto;
import org.example.product.core.domain.produto.enums.TipoProduto;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.Spy;
import org.modelmapper.ModelMapper;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ActiveProfiles("test")
@SpringBootTest
class ProdutoGatewayTest {

    @Mock
    private  ProdutoRepository produtoRepository;

    @Spy
    private  ModelMapper modelMapper;

    @InjectMocks
    private ProdutoGateway produtoGateway;

    Produto produto;

    ProdutoEntity produtoEntity;

    @Test
    void whenUpdateProductButNotExistsThenReturnThrow() {

        assertThrows(EntityNotFoundException.class, () -> produtoGateway.update(null, null, null, null));
    }

    @Test
    void whenUpdateProductThenReturnProduct() {

        when(produtoRepository.findById(produtoEntity.getId())).thenReturn(Optional.of(produtoEntity));

        produtoGateway.update(produtoEntity.getId(), produtoEntity.getNome(), produtoEntity.getPreco(), produtoEntity.getTipoProduto());

        verify(produtoRepository).save(produtoEntity);
    }

    @Test
    void whenDeleteProductThenDeleteProduct() {
        UUID id = UUID.fromString("f5d4b3d2-3b4c-4a3d-8f5e-2b1c4d3e4f5a");

        produtoGateway.deleteProdutoById(id);
        verify(produtoRepository).deleteById(id);
    }

    @Test
    void whenSaveProductThenSaveProduct() {
        when(modelMapper.map(produto, ProdutoEntity.class)).thenReturn(produtoEntity);
        when(produtoRepository.save(produtoEntity)).thenReturn(produtoEntity);
        when(modelMapper.map(produtoEntity, Produto.class)).thenReturn(produto);

        Produto result = produtoGateway.saveProduto(produto);

        verify(produtoRepository).save(produtoEntity);

        assertNotNull(result);
        assertEquals(produto.getNome(), result.getNome());
        assertEquals(produto.getPreco(), result.getPreco());
    }

    @Test
    void whenGetProductByTypeThenReturnProductList() {
        when(produtoRepository.findByTipoProduto(TipoProduto.BEBIDA)).thenReturn(List.of(produtoEntity));
        when(modelMapper.map(produtoEntity, Produto.class)).thenReturn(produto);

        List<Produto> result = produtoGateway.getProdutoByTipoProduto(TipoProduto.BEBIDA);

        assertNotNull(result);
        assertEquals(1, result.size());
        assertEquals(produto.getNome(), result.getFirst().getNome());
        assertEquals(produto.getPreco(), result.getFirst().getPreco());
    }

    @Test
    void whenGetProductByIdThenReturnProduct() {
        when(produtoRepository.findById(produtoEntity.getId())).thenReturn(Optional.of(produtoEntity));
        when(modelMapper.map(produtoEntity, Produto.class)).thenReturn(produto);

        Produto result = produtoGateway.getById(produtoEntity.getId());

        assertNotNull(result);
        assertEquals("Produto 1", result.getNome());
        assertEquals(BigDecimal.TEN, result.getPreco());
        assertEquals(TipoProduto.BEBIDA, result.getTipoProduto());
    }

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);

        produtoEntity = new ProdutoEntity();
        produtoEntity.setId(UUID.fromString("f5d4b3d2-3b4c-4a3d-8f5e-2b1c4d3e4f5a"));
        produtoEntity.setNome("Produto 1");
        produtoEntity.setPreco(BigDecimal.TEN);
        produtoEntity.setTipoProduto(TipoProduto.BEBIDA);

        produto = new Produto();
        produto.setId(UUID.fromString("f5d4b3d2-3b4c-4a3d-8f5e-2b1c4d3e4f5a"));
        produto.setNome("Produto 1");
        produto.setPreco(BigDecimal.TEN);
        produto.setTipoProduto(TipoProduto.BEBIDA);

    }

}