package org.example.product.application.controllers.produto.create;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.example.product.adapters.controllers.ProdutoController;
import org.example.product.adapters.presenters.ProdutoPresenter;
import org.example.product.core.applications.produto.repositories.ProdutoRepositoryInterface;
import org.example.product.core.domain.produto.Produto;
import org.example.product.core.domain.produto.enums.TipoProduto;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockedStatic;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(MockitoExtension.class)
class CreateProdutoControllerTest {

    @Mock
    private ProdutoRepositoryInterface produtoRepositoryInterface;

    @InjectMocks
    private CreateProdutoController createProdutoController;

    private ProdutoController produtoController;

    private MockMvc mockMvc;

    private final ObjectMapper objectMapper = new ObjectMapper();

    @Test
    void create() throws Exception {
        // Criando o produto para o teste
        Produto produto = new Produto(UUID.randomUUID(), "Produto 1", BigDecimal.TEN, TipoProduto.BEBIDA);

        // Criando o mock de resposta que será retornado pelo método estático
        Map<String, Object> mockResponse = new HashMap<>();
        mockResponse.put("id", produto.getId());
        mockResponse.put("nome", produto.getNome());
        mockResponse.put("preco", produto.getPreco());
        mockResponse.put("tipo_produto", produto.getTipoProduto());

        // Mockando o método estático ProdutoPresenter.toObject()
        try (MockedStatic<ProdutoPresenter> mockedStatic = mockStatic(ProdutoPresenter.class)) {
            // Quando ProdutoPresenter.toObject(produto) for chamado, retornar o mockResponse
            mockedStatic.when(() -> ProdutoPresenter.toObject(any(Produto.class))).thenReturn(mockResponse);

            // Mockando o método salvar() para garantir que o comportamento do controller seja mockado
            when(produtoController.salvar(produto.getNome(), produto.getPreco(), produto.getTipoProduto()))
                    .thenReturn(mockResponse);

            // Verificando o estado do produto antes de chamar ProdutoPresenter.toObject
            Produto produtoTest = new Produto(produto.getId(), produto.getNome(), produto.getPreco(), produto.getTipoProduto());
            System.out.println("Produto antes de chamar toObject: " + produtoTest);

            // Realizando o POST e verificando apenas o status HTTP
            mockMvc.perform(post("/produtos")
                            .contentType(MediaType.APPLICATION_JSON)
                            .content(objectMapper.writeValueAsString(produto)))
                    .andExpect(status().isCreated()); // Validando apenas o status HTTP
        }

    }


    @BeforeEach
    void setUp() {
        // Inicializando os mocks
        MockitoAnnotations.openMocks(this);

        // Criando o ProdutoController com a dependência mockada
        produtoController = new ProdutoController(produtoRepositoryInterface);

        // Configurando o MockMvc para testar o controller
        this.mockMvc = MockMvcBuilders.standaloneSetup(createProdutoController).build();
    }
}