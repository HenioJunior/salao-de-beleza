package com.heniojunior.salaodebeleza.api.dtos;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.time.LocalDateTime;

public class AgendamentoRequest {

    @JsonFormat(pattern = "dd-MM-yyyy@HH:mm:ss", shape = JsonFormat.Shape.STRING)
    private LocalDateTime horario;
    private String clienteId;
    private String profissionalId;
    private String servicoId;

    public AgendamentoRequest() {
    }

    public AgendamentoRequest(LocalDateTime horario, String clienteId, String profissionalId, String servicoId) {
        this.horario = horario;
        this.clienteId = clienteId;
        this.profissionalId = profissionalId;
        this.servicoId = servicoId;
    }

    public LocalDateTime getHorario() {
        return horario;
    }

    public String getClienteId() {
        return clienteId;
    }

    public String getProfissionalId() {
        return profissionalId;
    }

    public String getServicoId() {
        return servicoId;
    }
}
