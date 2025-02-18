package com.heniojunior.salaodebeleza.api.entities;

import com.fasterxml.jackson.annotation.JsonFormat;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;
import java.util.UUID;

@Document(collection = "agendamentos")
public class Agendamento {

    @Id
    private String id;
    @JsonFormat(pattern = "dd-MM-yyyy@HH:mm:ss", shape = JsonFormat.Shape.STRING)
    private LocalDateTime horario;
    private Cliente cliente;
    private Profissional profissional;
    private Servico servico;

    public Agendamento(LocalDateTime horario, Cliente cliente, Profissional profissional, Servico servico) {
        this.id = UUID.randomUUID().toString();
        this.horario = horario;
        this.cliente = cliente;
        this.profissional = profissional;
        this.servico = servico;
    }

    public String getId() {
        return id;
    }

    public LocalDateTime getHorario() {
        return horario;
    }

    public void setHorario(LocalDateTime horario) {
        this.horario = horario;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public Profissional getProfissional() {
        return profissional;
    }

    public void setProfissional(Profissional profissional) {
        this.profissional = profissional;
    }

    public Servico getServico() {
        return servico;
    }

    public void setServico(Servico servico) {
        this.servico = servico;
    }
}
