package org.example.product.application.driven.entities.produto;

import jakarta.persistence.*;
import lombok.Data;
import org.example.product.core.domain.produto.enums.TipoProduto;

import java.math.BigDecimal;
import java.util.UUID;

@Data
@Entity
@Table(name = "TB_PRODUTO")
public class ProdutoEntity {

    @Id
    @GeneratedValue(generator = "UUID")
    private UUID id;

    private String nome;

    private BigDecimal preco;

    @Enumerated(EnumType.STRING)
    private TipoProduto tipoProduto;

}
