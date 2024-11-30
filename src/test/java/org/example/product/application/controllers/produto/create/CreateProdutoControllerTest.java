package org.example.product.application.controllers.produto.create;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.example.product.adapters.presenters.ProdutoPresenter;
import org.example.product.core.applications.produto.repositories.ProdutoRepositoryInterface;
import org.example.product.core.domain.produto.Produto;
import org.example.product.core.domain.produto.enums.TipoProduto;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockedStatic;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import static org.mockito.Mockito.any;
import static org.mockito.Mockito.mockStatic;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@ActiveProfiles("test")
@SpringBootTest
class CreateProdutoControllerTest {

    @Mock
    private ProdutoRepositoryInterface produtoRepositoryInterface;

    private MockMvc mockMvc;

    private final ObjectMapper objectMapper = new ObjectMapper();

    @Test
    void whenCreateProductThenReturn200() throws Exception {
        Produto produto = new Produto(UUID.randomUUID(), "Produto 1", BigDecimal.TEN, TipoProduto.BEBIDA);

        Map<String, Object> mockResponse = new HashMap<>();
        mockResponse.put("id", produto.getId());
        mockResponse.put("nome", produto.getNome());
        mockResponse.put("preco", produto.getPreco());
        mockResponse.put("tipo_produto", produto.getTipoProduto());

        try (MockedStatic<ProdutoPresenter> mockedStatic = mockStatic(ProdutoPresenter.class)) {
            mockedStatic.when(() -> ProdutoPresenter.toObject(any(Produto.class))).thenReturn(mockResponse);

            mockMvc.perform(post("/produtos")
                            .contentType(MediaType.APPLICATION_JSON)
                            .content(objectMapper.writeValueAsString(produto)))
                    .andExpect(status().isCreated());
        }
    }

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        CreateProdutoController createProdutoController = new CreateProdutoController(produtoRepositoryInterface);
        this.mockMvc = MockMvcBuilders.standaloneSetup(createProdutoController).build();

    }
}