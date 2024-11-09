package org.example.product.application.controllers.produto.update;


import io.swagger.v3.oas.annotations.Operation;
import lombok.AllArgsConstructor;
import org.example.product.adapters.controllers.ProdutoController;
import org.example.product.application.controllers.produto.update.requests.ProdutoUpdateRequest;
import org.example.product.core.applications.produto.repositories.ProdutoRepositoryInterface;
import org.example.product.core.domain.produto.Produto;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("produtos")
@AllArgsConstructor
public class UpdateProdutoController {

    private final ProdutoRepositoryInterface produtoRepositoryInterface;

    @PutMapping("/{id}")
    @Operation(tags = "Produtos")
    public ResponseEntity<Produto> update(@PathVariable("id") UUID id, @RequestBody ProdutoUpdateRequest request) {
        ProdutoController produtoController = new ProdutoController(this.produtoRepositoryInterface);
        produtoController.atualizar(id, request.nome(), request.preco(), request.tipoProduto());
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

}
