package com.heniojunior.salaodebeleza.api.controllers;

import com.heniojunior.salaodebeleza.api.dtos.ServicoDto;
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

@RestController
@RequestMapping(value = "/servicos", produces = {"application/json"})
@Tag(name = "salao-de-beleza")
public class ServicoController {

    @PersistenceContext
    private EntityManager manager;

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    @Operation(summary = "Realiza o cadastro de um novo servico")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Servico cadastrado com sucesso"),
            @ApiResponse(responseCode = "422", description = "Dados inválidos"),
            @ApiResponse(responseCode = "400", description = "Parâmetros inválidos"),
            @ApiResponse(responseCode = "500", description = "Erro ao realizar o cadastro")
    })
    @Transactional
    public ResponseEntity<String> novoServico(@RequestBody ServicoDto dto) {
        Servico servico = dto.toModel();
        manager.persist(servico);
        return ResponseEntity.ok("Servico cadastrado com sucesso.");
    }

    @PutMapping(value = {"/{id}"}, consumes = MediaType.APPLICATION_JSON_VALUE)
    @Operation(summary = "Modifica o cadastro de um servico existente")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Cadastro do servico atualizado com sucesso"),
            @ApiResponse(responseCode = "422", description = "Dados inválidos"),
            @ApiResponse(responseCode = "400", description = "Parâmetros inválidos"),
            @ApiResponse(responseCode = "500", description = "Erro ao atualizar o cadastro do servico")
    })
    @Transactional
    public ResponseEntity<String> atualizaServico(@PathVariable int id, @RequestBody ServicoDto dto) {
        Servico servico = manager.find(Servico.class, id);
        copydtoToEntity(dto, servico);
        manager.persist(servico);
        return ResponseEntity.ok("Serviço atualizado com sucesso.");
    }

    @DeleteMapping(value = {"/{id}"}, consumes = MediaType.APPLICATION_JSON_VALUE)
    @Operation(summary = "Remove um servico existente")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Servico removido com sucesso"),
            @ApiResponse(responseCode = "422", description = "Dados inválidos"),
            @ApiResponse(responseCode = "400", description = "Parâmetros inválidos"),
            @ApiResponse(responseCode = "500", description = "Erro ao remover o servico")
    })
    @Transactional
    public ResponseEntity<Void> deletaServico(@PathVariable Long id) {
        Servico servico = manager.find(Servico.class, id);
        manager.remove(servico);
        return ResponseEntity.noContent().build();
    }

    private void copydtoToEntity(ServicoDto dto, Servico servico) {
        servico.setTipo(dto.getTipo());
        servico.setValor(dto.getValor());
    }
}
