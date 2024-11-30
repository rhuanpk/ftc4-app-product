package org.example.product.application.controllers.produto.delete;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.example.product.application.controllers.produto.findTipoProduto.FindTipoProdutoProdutoController;
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

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ActiveProfiles("test")
@SpringBootTest
class DeleteProdutoControllerTest {

    @Mock
    private ProdutoRepositoryInterface produtoRepositoryInterface;

    private MockMvc mockMvc;

    @Test
    void whenDeleteProductThenReturn204() throws Exception {

        mockMvc.perform(delete("/produtos/{id}", UUID.randomUUID()))
                .andExpect(status().isNoContent());
    }



    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        DeleteProdutoController deleteProdutoController = new DeleteProdutoController(produtoRepositoryInterface);
        this.mockMvc = MockMvcBuilders.standaloneSetup(deleteProdutoController).build();

    }

}