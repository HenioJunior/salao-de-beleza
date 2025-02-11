package com.heniojunior.salaodebeleza.api.dtos;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.heniojunior.salaodebeleza.api.entities.Agendamento;
import com.heniojunior.salaodebeleza.api.entities.Cliente;
import com.heniojunior.salaodebeleza.api.entities.Profissional;
import com.heniojunior.salaodebeleza.api.entities.Servico;
import jakarta.persistence.EntityManager;

import java.time.LocalDateTime;

public class AgendamentoDto {

    private long id;
    @JsonFormat(pattern = "dd-MM-yyyy@HH:mm:ss", shape = JsonFormat.Shape.STRING)
    private LocalDateTime horario;
    private long profissionalId;
    private long clienteId;
    private long servicoId;

    public AgendamentoDto(LocalDateTime horario, long profissionalId, long clienteId, long servicoId) {
        this.horario = horario;
        this.profissionalId = profissionalId;
        this.clienteId = clienteId;
        this.servicoId = servicoId;
    }

    public long getId() {
        return id;
    }

    public LocalDateTime getHorario() {
        return horario;
    }

    public long getProfissionalId() {
        return profissionalId;
    }

    public long getClienteId() {
        return clienteId;
    }

    public long getServicoId() {
        return servicoId;
    }

    public Agendamento toModel(EntityManager manager) {
        Cliente cliente = manager.find(Cliente.class, clienteId);
        Profissional profissional = manager.find(Profissional.class, profissionalId);
        Servico servico = manager.find(Servico.class, servicoId);

        return new Agendamento(horario, profissional, cliente, servico);
    }
}
