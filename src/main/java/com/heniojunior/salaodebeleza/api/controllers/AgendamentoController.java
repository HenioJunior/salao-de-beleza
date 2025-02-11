package com.heniojunior.salaodebeleza.api.controllers;

import com.heniojunior.salaodebeleza.api.dtos.AgendamentoDto;
import com.heniojunior.salaodebeleza.api.entities.Agendamento;
import com.heniojunior.salaodebeleza.api.entities.Cliente;
import com.heniojunior.salaodebeleza.api.entities.Profissional;
import com.heniojunior.salaodebeleza.api.entities.Servico;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;

@RestController
@RequestMapping(value = "/agendamentos", produces = {"application/json"})
@Tag(name = "salao-de-beleza")
public class AgendamentoController {

    @PersistenceContext
    private EntityManager manager;

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    @Operation(summary = "Realiza um novo agendamento de serviço")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Agendamento realizado com sucesso"),
            @ApiResponse(responseCode = "422", description = "Dados inválidos"),
            @ApiResponse(responseCode = "400", description = "Parâmetros inválidos"),
            @ApiResponse(responseCode = "500", description = "Erro ao realizar o agendamento")
    })
    @Transactional
    public ResponseEntity<String> novaAgendamento(@RequestBody AgendamentoDto request) {
        Agendamento agendamento = request.toModel(manager);
        manager.persist(agendamento);
        return ResponseEntity.ok("Agendamento realizado com sucesso.");
    }

    @PutMapping(value = {"/{id}"}, consumes = MediaType.APPLICATION_JSON_VALUE)
    @Operation(summary = "Modifica um agendamento existente")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Agendamento atualizado com sucesso"),
            @ApiResponse(responseCode = "422", description = "Dados inválidos"),
            @ApiResponse(responseCode = "400", description = "Parâmetros inválidos"),
            @ApiResponse(responseCode = "500", description = "Erro ao atualizar o agendamento")
    })
    @Transactional
    public ResponseEntity<AgendamentoDto> atualizaAgendamento(@PathVariable long id, @RequestBody AgendamentoDto dto) {
        Agendamento agendamento = manager.find(Agendamento.class, id);
        copydtoToEntity(dto, agendamento);
        manager.persist(agendamento);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
                .buildAndExpand(id).toUri();
        return ResponseEntity.created(uri).body(dto);
    }

    @DeleteMapping(value = {"/{id}"}, consumes = MediaType.APPLICATION_JSON_VALUE)
    @Operation(summary = "Remove um agendamento existente")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Agendamento removido com sucesso"),
            @ApiResponse(responseCode = "422", description = "Dados inválidos"),
            @ApiResponse(responseCode = "400", description = "Parâmetros inválidos"),
            @ApiResponse(responseCode = "500", description = "Erro ao remover o agendamento")
    })
    @Transactional
    public ResponseEntity<Void> deletaAgendamento(@PathVariable Long id) {
        Agendamento agendamento = manager.find(Agendamento.class, id);
        manager.remove(agendamento);
        return ResponseEntity.noContent().build();
    }

    private void copydtoToEntity(AgendamentoDto dto, Agendamento agendamento) {
        Agendamento a = dto.toModel(manager);

        agendamento.setHorario(dto.getHorario());
        agendamento.setCliente(a.getCliente());
        agendamento.setProfissional(a.getProfissional());
        agendamento.setServico(a.getServico());
    }
}
