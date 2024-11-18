package org.example.product.application.controllers.produto.update;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.example.product.adapters.presenters.ProdutoPresenter;
import org.example.product.application.controllers.produto.findTipoProduto.FindTipoProdutoProdutoController;
import org.example.product.core.applications.produto.repositories.ProdutoRepositoryInterface;
import org.example.product.core.applications.produto.usecases.AtualizarProduto;
import org.example.product.core.domain.produto.Produto;
import org.example.product.core.domain.produto.enums.TipoProduto;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockedStatic;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mockStatic;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
class UpdateProdutoControllerTest {

    @Mock
    private ProdutoRepositoryInterface produtoRepositoryInterface;

    private MockMvc mockMvc;

    private final ObjectMapper objectMapper = new ObjectMapper();

    @Test
    void whenUpdateProductThenReturn204() throws Exception {
        Produto produto = new Produto(UUID.randomUUID(), "Produto 1", BigDecimal.TEN, TipoProduto.BEBIDA);

        mockMvc.perform(put("/produtos/{id}", produto.getId())
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(produto)))
                .andExpect(status().isNoContent());
    }
    
    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        UpdateProdutoController updateProdutoController = new UpdateProdutoController(produtoRepositoryInterface);
        this.mockMvc = MockMvcBuilders.standaloneSetup(updateProdutoController).build();

    }

}