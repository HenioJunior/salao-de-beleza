package com.heniojunior.salaodebeleza.api.repositories;

import com.heniojunior.salaodebeleza.api.entities.Produto;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface ProdutoRepository extends MongoRepository<Produto, String> {
}
