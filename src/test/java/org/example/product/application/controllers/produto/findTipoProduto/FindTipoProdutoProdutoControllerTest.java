package org.example.product.application.controllers.produto.findTipoProduto;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.example.product.application.controllers.produto.create.CreateProdutoController;
import org.example.product.core.applications.produto.repositories.ProdutoRepositoryInterface;
import org.example.product.core.domain.produto.Produto;
import org.example.product.core.domain.produto.enums.TipoProduto;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.math.BigDecimal;
import java.util.List;
import java.util.UUID;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@ActiveProfiles("test")
@SpringBootTest
class FindTipoProdutoProdutoControllerTest {

    @Mock
    private ProdutoRepositoryInterface produtoRepositoryInterface;

    private MockMvc mockMvc;

    @Test
    void whenFindByProductTypeIsEmptyThenReturn200() throws Exception {

        when(produtoRepositoryInterface.getProdutoByTipoProduto(TipoProduto.BEBIDA))
                .thenReturn(List.of(new Produto(UUID.randomUUID(), "Produto 1", BigDecimal.TEN, TipoProduto.BEBIDA)));

        mockMvc.perform(get("/produtos/tipo/{tipoProduto}", TipoProduto.BEBIDA))
                .andExpect(status().isOk());
    }


    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        FindTipoProdutoProdutoController findTipoProdutoProdutoController = new FindTipoProdutoProdutoController(produtoRepositoryInterface);
        this.mockMvc = MockMvcBuilders.standaloneSetup(findTipoProdutoProdutoController).build();

    }

}