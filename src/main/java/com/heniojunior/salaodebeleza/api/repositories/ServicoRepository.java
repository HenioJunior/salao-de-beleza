package com.heniojunior.salaodebeleza.api.repositories;

import com.heniojunior.salaodebeleza.api.entities.Servico;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface ServicoRepository extends MongoRepository<Servico, String> {

}
