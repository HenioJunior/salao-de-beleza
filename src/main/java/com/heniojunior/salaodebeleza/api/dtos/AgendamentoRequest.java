package com.heniojunior.salaodebeleza.api.dtos;

import com.heniojunior.salaodebeleza.api.entities.Agendamento;
import com.heniojunior.salaodebeleza.api.entities.Cliente;
import com.heniojunior.salaodebeleza.api.entities.Profissional;
import com.heniojunior.salaodebeleza.api.entities.Servico;
import jakarta.persistence.EntityManager;

import java.time.LocalDateTime;

public class AgendamentoRequest {

    private LocalDateTime horario;
    private int profissionalId;
    private int clienteId;
    private int servicoId;

    public AgendamentoRequest(LocalDateTime horario, int profissionalId, int clienteId, int servicoId) {
        this.horario = horario;
        this.profissionalId = profissionalId;
        this.clienteId = clienteId;
        this.servicoId = servicoId;
    }

    public LocalDateTime getHorario() {
        return horario;
    }

    public int getProfissionalId() {
        return profissionalId;
    }

    public int getClienteId() {
        return clienteId;
    }

    public int getServicoId() {
        return servicoId;
    }

    public Agendamento toModel(EntityManager manager) {
        Cliente cliente = manager.find(Cliente.class, clienteId);
        Profissional profissional = manager.find(Profissional.class, profissionalId);
        Servico servico = manager.find(Servico.class, servicoId);

        return new Agendamento(horario, profissional, cliente, servico);
    }
}
