package com.heniojunior.salaodebeleza.api.services;

import com.heniojunior.salaodebeleza.api.dtos.ProdutoRequest;
import com.heniojunior.salaodebeleza.api.dtos.ProdutoResponse;
import com.heniojunior.salaodebeleza.api.entities.Produto;
import com.heniojunior.salaodebeleza.api.repositories.ProdutoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Service
public class ProdutoService {

    @Autowired
    private ProdutoRepository repository;

    @Transactional(readOnly = true)
    public ProdutoResponse buscaPorId(String id) {
        Produto produto = repository.findById(id).orElseThrow(
                () -> new RuntimeException("Recurso não encontrado"));
        return new ProdutoResponse(produto);
    }

    @Transactional(readOnly = true)
    public Page<ProdutoResponse> buscaTodos(Pageable pageable) {
        Page<Produto> result = repository.findAll(pageable);
        return result.map(ProdutoResponse::new);
    }

    public ProdutoResponse novoProduto(ProdutoRequest request) {
        Produto produto = new Produto(request.getNome(), request.getValor(), request.getTipo());
        repository.save(produto);
        return new ProdutoResponse(produto);
    }

    public ProdutoResponse atualizaProduto(String id, ProdutoRequest request) {
        Produto produto = repository.findById(id).orElseThrow(()-> new RuntimeException("Produto não localizado"));
        produto.setNome(request.getNome());
        produto.setValor(request.getValor());
        produto.setTipo(request.getTipo());
        repository.save(produto);
        return new ProdutoResponse(produto);
    }

    @Transactional(propagation = Propagation.SUPPORTS)
    public void deletaProduto(String id) {
        if(!repository.existsById(id)) {
            throw new RuntimeException("Recurso não encontrado");
        }
        try {
            repository.deleteById(id);
        } catch (DataIntegrityViolationException e) {
            throw new RuntimeException("Falha de integridade referencial");
        }
    }
}
