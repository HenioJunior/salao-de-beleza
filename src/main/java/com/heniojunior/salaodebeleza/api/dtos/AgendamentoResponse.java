package com.heniojunior.salaodebeleza.api.dtos;

import com.heniojunior.salaodebeleza.api.entities.Agendamento;
import com.heniojunior.salaodebeleza.api.entities.Cliente;
import com.heniojunior.salaodebeleza.api.entities.Profissional;
import com.heniojunior.salaodebeleza.api.entities.Servico;

import java.time.LocalDateTime;

public class AgendamentoResponse {


    private String id;
    private LocalDateTime horario;
    private Cliente cliente;
    private Profissional profissional;
    private Servico servico;


    public AgendamentoResponse(Agendamento agendamento) {
        this.id = agendamento.getId();
        this.horario = agendamento.getHorario();
        this.cliente = agendamento.getCliente();
        this.profissional = agendamento.getProfissional();
        this.servico = agendamento.getServico();
    }

    public String getId() {
        return id;
    }

    public LocalDateTime getHorario() {
        return horario;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public Profissional getProfissional() {
        return profissional;
    }

    public Servico getServico() {
        return servico;
    }
}
