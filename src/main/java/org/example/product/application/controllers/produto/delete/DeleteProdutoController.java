package org.example.product.application.controllers.produto.delete;


import io.swagger.v3.oas.annotations.Operation;
import lombok.AllArgsConstructor;
import org.example.product.adapters.controllers.ProdutoController;
import org.example.product.core.applications.produto.repositories.ProdutoRepositoryInterface;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@RestController
@RequestMapping("produtos")
@AllArgsConstructor
public class DeleteProdutoController {

    private final ProdutoRepositoryInterface produtoRepositoryInterface;

    @DeleteMapping("/{id}")
    @Operation(tags = "Produtos")
    public ResponseEntity<Void> delete(@PathVariable("id") UUID id) {
        ProdutoController produtoController = new ProdutoController(this.produtoRepositoryInterface);
        produtoController.deletar(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

}
