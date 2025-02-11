package com.heniojunior.salaodebeleza.api.entities;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
public class Agendamento {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @JsonFormat(pattern = "dd-MM-yyyy@HH:mm:ss", shape = JsonFormat.Shape.STRING)
    private LocalDateTime horario;
    @ManyToOne
    private Profissional profissional;
    @ManyToOne
    private Cliente cliente;
    @ManyToOne
    private Servico servico;

    public Agendamento(LocalDateTime horario, Profissional profissional, Cliente cliente, Servico servico) {
        this.horario = horario;
        this.profissional = profissional;
        this.cliente = cliente;
        this.servico = servico;
    }

    public Agendamento() {

    }

    public Long getId() {
        return id;
    }

    public LocalDateTime getHorario() {
        return horario;
    }

    public void setHorario(LocalDateTime horario) {
        this.horario = horario;
    }

    public Profissional getProfissional() {
        return profissional;
    }

    public void setProfissional(Profissional profissional) {
        this.profissional = profissional;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public Servico getServico() {
        return servico;
    }

    public void setServico(Servico servico) {
        this.servico = servico;
    }
}
