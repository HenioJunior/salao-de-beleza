package com.heniojunior.salaodebeleza.api.repositories;

import com.heniojunior.salaodebeleza.api.entities.Agendamento;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface AgendamentoRepository extends MongoRepository<Agendamento, String> {

}
