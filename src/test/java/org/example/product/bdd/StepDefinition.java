package org.example.product.bdd;

import io.cucumber.java.pt.Dado;
import io.cucumber.java.pt.E;
import io.cucumber.java.pt.Entao;
import org.example.product.application.controllers.produto.create.requests.ProdutoCreateRequest;
import org.example.product.core.domain.produto.enums.TipoProduto;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;

import java.math.BigDecimal;
import java.net.URISyntaxException;

import static org.junit.jupiter.api.Assertions.assertEquals;

@ActiveProfiles("test")
public class StepDefinition {

    private ProdutoCreateRequest produto;
    private ResponseEntity<String> response;
    private MockMvc mockMvc;
    TestRestTemplate restTemplate = new TestRestTemplate();
    String url = "http://a667981233b704083af07f2232f4a0db-134519073.us-east-1.elb.amazonaws.com:4000/produtos";


    @Dado("que tenho um produto válido")
    public void que_tenho_um_produto_válido() {
        produto = new ProdutoCreateRequest("Hamburguer", BigDecimal.TEN, TipoProduto.LANCHE);
    }
    @E("envio uma requisição POST para criar o produto")
    public void envio_uma_requisição_post_para_criar_o_produto() throws URISyntaxException {
        HttpEntity<ProdutoCreateRequest> request = new HttpEntity<>(produto);
        response = restTemplate.exchange(url, HttpMethod.POST, request, String.class);
    }
    @Entao("o sistema deve retornar o status created")
    public void o_sistema_deve_retornar_o_status_created() {
        assertEquals(201, response.getStatusCode().value());
    }
}
