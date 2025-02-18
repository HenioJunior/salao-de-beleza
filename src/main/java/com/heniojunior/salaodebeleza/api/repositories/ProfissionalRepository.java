package com.heniojunior.salaodebeleza.api.repositories;

import com.heniojunior.salaodebeleza.api.entities.Profissional;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface ProfissionalRepository extends MongoRepository<Profissional, String> {
}
