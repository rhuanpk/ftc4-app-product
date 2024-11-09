package org.example.product.application.controllers.produto.create;

import io.swagger.v3.oas.annotations.Operation;
import org.example.product.adapters.controllers.ProdutoController;
import org.example.product.application.controllers.produto.create.requests.ProdutoCreateRequest;
import org.example.product.core.applications.produto.repositories.ProdutoRepositoryInterface;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("produtos")
public class CreateProdutoController {

    private final ProdutoRepositoryInterface produtoRepositoryInterface;

    @PostMapping
    @Operation(tags = "Produtos")
    public ResponseEntity<Object> create(@RequestBody ProdutoCreateRequest request) {
        ProdutoController produtoController = new ProdutoController(this.produtoRepositoryInterface);
        return new ResponseEntity<>(produtoController.salvar(request.nome(), request.preco(), request.tipoProduto()), HttpStatus.CREATED);
    }
}
