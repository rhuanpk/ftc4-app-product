package org.example.product.application.controllers.produto.findTipoProduto;

import io.swagger.v3.oas.annotations.Operation;
import lombok.AllArgsConstructor;
import org.example.product.adapters.controllers.ProdutoController;
import org.example.product.core.applications.produto.repositories.ProdutoRepositoryInterface;
import org.example.product.core.domain.produto.enums.TipoProduto;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("produtos")
@AllArgsConstructor
public class FindTipoProdutoProdutoController {

    private final ProdutoRepositoryInterface produtoRepositoryInterface;

    @GetMapping("/tipo/{tipoProduto}")
    @Operation(tags = "Produtos")
    public ResponseEntity<Object> findByTipoProduto(@PathVariable("tipoProduto") TipoProduto tipoProduto) {
        ProdutoController produtoController = new ProdutoController(this.produtoRepositoryInterface);
        return new ResponseEntity<>(produtoController.findByTipoProduto(tipoProduto), HttpStatus.OK);
    }

}
