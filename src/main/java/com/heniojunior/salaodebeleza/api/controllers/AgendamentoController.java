package com.heniojunior.salaodebeleza.api.controllers;

import com.heniojunior.salaodebeleza.api.dtos.AgendamentoRequest;
import com.heniojunior.salaodebeleza.api.dtos.AgendamentoResponse;
import com.heniojunior.salaodebeleza.api.entities.Agendamento;
import com.heniojunior.salaodebeleza.api.services.AgendamentoService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;

@RestController
@RequestMapping(value = "/agendamentos", produces = {"application/json"})
@Tag(name = "salao-de-beleza")
public class AgendamentoController {

    @Autowired
    private AgendamentoService service;


    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    @Operation(summary = "Realiza um novo agendamento de serviço")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Agendamento realizado com sucesso"),
            @ApiResponse(responseCode = "422", description = "Dados inválidos"),
            @ApiResponse(responseCode = "400", description = "Parâmetros inválidos"),
            @ApiResponse(responseCode = "500", description = "Erro ao realizar o agendamento")
    })
    public ResponseEntity<AgendamentoResponse> novaAgendamento(@RequestBody AgendamentoRequest request) {
        AgendamentoResponse response = service.novoAgendamento(request);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}").buildAndExpand(response.getId()).toUri();
        return ResponseEntity.created(uri).body(response);
    }

    @PutMapping(value = {"/{id}"}, consumes = MediaType.APPLICATION_JSON_VALUE)
    @Operation(summary = "Modifica um agendamento existente")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Agendamento atualizado com sucesso"),
            @ApiResponse(responseCode = "422", description = "Dados inválidos"),
            @ApiResponse(responseCode = "400", description = "Parâmetros inválidos"),
            @ApiResponse(responseCode = "500", description = "Erro ao atualizar o agendamento")
    })
    public ResponseEntity<AgendamentoResponse> atualizaAgendamento(@PathVariable String id, @RequestBody AgendamentoRequest request) {
        AgendamentoResponse response = service.atualizaAgendamento(id, request);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
                .buildAndExpand(id).toUri();
        return ResponseEntity.created(uri).body(response);
    }

    @DeleteMapping(value = {"/{id}"}, consumes = MediaType.APPLICATION_JSON_VALUE)
    @Operation(summary = "Remove um agendamento existente")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Agendamento removido com sucesso"),
            @ApiResponse(responseCode = "422", description = "Dados inválidos"),
            @ApiResponse(responseCode = "400", description = "Parâmetros inválidos"),
            @ApiResponse(responseCode = "500", description = "Erro ao remover o agendamento")
    })
    public ResponseEntity<Void> deletaAgendamento(@PathVariable Long id) {
        service.deletaAgendamento(id);
        return ResponseEntity.noContent().build();
    }

}