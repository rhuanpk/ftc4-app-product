package org.example.product.adapters.gateways;


import lombok.AllArgsConstructor;
import org.example.product.application.driven.entities.produto.ProdutoEntity;
import org.example.product.application.driven.repositories.produto.ProdutoRepository;
import org.example.product.core.applications.exception.EntityNotFoundException;
import org.example.product.core.applications.produto.repositories.ProdutoRepositoryInterface;
import org.example.product.core.domain.produto.Produto;
import org.example.product.core.domain.produto.enums.TipoProduto;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Component
@AllArgsConstructor
public class ProdutoGateway implements ProdutoRepositoryInterface {

    private ProdutoRepository produtoRepository;
    private ModelMapper modelMapper;

    @Override
    public Produto saveProduto(Produto produto) {
        ProdutoEntity entity = modelMapper.map(produto, ProdutoEntity.class);
        entity = produtoRepository.save(entity);
        return modelMapper.map(entity, Produto.class);
    }

    @Override
    public List<Produto> getProdutoByTipoProduto(TipoProduto tipoProduto) {
        List<ProdutoEntity> produtoEntities = produtoRepository.findByTipoProduto(tipoProduto);
        return produtoEntities.stream()
                .map(entity -> modelMapper.map(entity, Produto.class))
                .toList();
    }

    @Override
    public void deleteProdutoById(UUID id) {
        produtoRepository.deleteById(id);
    }

    @Override
    public void update(UUID id, String nome, BigDecimal preco, TipoProduto tipoProduto) {
        Optional<ProdutoEntity> entity = produtoRepository.findById(id);
        if(entity.isEmpty()){
            throw new EntityNotFoundException("Produto não encontrado");
        } else {
            ProdutoEntity produtoEntity = entity.get();
            produtoEntity.setNome(nome);
            produtoEntity.setPreco(preco);
            produtoEntity.setTipoProduto(tipoProduto);
            produtoRepository.save(produtoEntity);

        }
    }

    @Override
    public Produto getById(UUID id) {
        Optional<ProdutoEntity> produtoEntity = this.produtoRepository.findById(id);
        return produtoEntity.map(this::converterProdutoEntityParaProduto).orElse(null);
    }

    public Produto converterProdutoEntityParaProduto(ProdutoEntity produtoEntity) {
        return modelMapper.map(produtoEntity, Produto.class);
    }
}
