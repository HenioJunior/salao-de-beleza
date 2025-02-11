package com.heniojunior.salaodebeleza.api.controllers;

import com.heniojunior.salaodebeleza.api.dtos.ProfissionalDto;
import com.heniojunior.salaodebeleza.api.entities.Profissional;
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
@RequestMapping(value = "/profissionais", produces = {"application/json"})
@Tag(name = "salao-de-beleza")
public class ProfissionalController {

    @PersistenceContext
    private EntityManager manager;

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    @Operation(summary = "Realiza o cadastro de um novo profissional")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Profissional cadastrado com sucesso"),
            @ApiResponse(responseCode = "422", description = "Dados inválidos"),
            @ApiResponse(responseCode = "400", description = "Parâmetros inválidos"),
            @ApiResponse(responseCode = "500", description = "Erro ao realizar o cadastro")
    })
    @Transactional
    public ResponseEntity<String> novoprofissional(@RequestBody ProfissionalDto dto) {
        Profissional profissional = dto.toModel();
        manager.persist(profissional);
        return ResponseEntity.ok("Profissional cadastrado com sucesso.");
    }

    @PutMapping(value = {"/{id}"}, consumes = MediaType.APPLICATION_JSON_VALUE)
    @Operation(summary = "Modifica o cadastro de um profissional existente")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Cadastro do profissional atualizado com sucesso"),
            @ApiResponse(responseCode = "422", description = "Dados inválidos"),
            @ApiResponse(responseCode = "400", description = "Parâmetros inválidos"),
            @ApiResponse(responseCode = "500", description = "Erro ao atualizar o cadastro do profissional")
    })
    @Transactional
    public ResponseEntity<String> atualizaprofissional(@PathVariable int id, @RequestBody ProfissionalDto dto) {
        Profissional profissional = manager.find(Profissional.class, id);
        copydtoToEntity(dto, profissional);
        manager.persist(profissional);
        return ResponseEntity.ok("Agendamento atualizado com sucesso.");
    }

    @DeleteMapping(value = {"/{id}"}, consumes = MediaType.APPLICATION_JSON_VALUE)
    @Operation(summary = "Remove um profissional existente")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Profissional removido com sucesso"),
            @ApiResponse(responseCode = "422", description = "Dados inválidos"),
            @ApiResponse(responseCode = "400", description = "Parâmetros inválidos"),
            @ApiResponse(responseCode = "500", description = "Erro ao remover o profissional")
    })
    @Transactional
    public ResponseEntity<Void> deletaProfissional(@PathVariable Long id) {
        Profissional profissional = manager.find(Profissional.class, id);
        manager.remove(profissional);
        return ResponseEntity.noContent().build();
    }

    private void copydtoToEntity(ProfissionalDto dto, Profissional profissional) {
        profissional.setNome(dto.getNome());
        profissional.setCpf(dto.getCpf());
        profissional.setEmail(dto.getEmail());
        profissional.setTelefone(dto.getTelefone());
    }
}
