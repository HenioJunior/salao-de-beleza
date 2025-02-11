package com.heniojunior.salaodebeleza.api.repositories;

import com.heniojunior.salaodebeleza.api.entities.Agendamento;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AgendamentoRepository extends JpaRepository<Agendamento, Long> {
}
