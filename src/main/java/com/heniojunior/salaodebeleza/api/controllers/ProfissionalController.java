package com.heniojunior.salaodebeleza.api.controllers;

import com.heniojunior.salaodebeleza.api.dtos.ProfissionalRequest;
import com.heniojunior.salaodebeleza.api.dtos.ProfissionalResponse;
import com.heniojunior.salaodebeleza.api.services.ProfissionalService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;

@RestController
@RequestMapping(value = "/profissionais", produces = {"application/json"})
@Tag(name = "Profissional")
public class ProfissionalController {

    @Autowired
    private ProfissionalService service;

    @GetMapping("/{id}")
    @Operation(summary = "Realiza a busca de um profissional por id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Ok"),

    })
    public ResponseEntity<ProfissionalResponse> findById(@PathVariable String id) {
        ProfissionalResponse response =  service.buscaPorId(id);
        return ResponseEntity.ok(response);
    }

    @GetMapping
    @Operation(summary = "Realiza a busca de todos os profissionais")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "ok"),

    })
    public ResponseEntity<Page<ProfissionalResponse>>  findAll(Pageable pageable) {
        Page<ProfissionalResponse> response =  service.buscaTodos(pageable);
        return ResponseEntity.ok(response);
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    @Operation(summary = "Realiza o cadastro de um novo profissional")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Profissional cadastrado com sucesso"),
            @ApiResponse(responseCode = "422", description = "Dados inválidos"),
            @ApiResponse(responseCode = "400", description = "Parâmetros inválidos"),
            @ApiResponse(responseCode = "500", description = "Erro ao realizar o cadastro")
    })
    public ResponseEntity<ProfissionalResponse> novoprofissional(@RequestBody ProfissionalRequest request) {
        ProfissionalResponse response = service.novoProfissional(request);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
                .buildAndExpand(response.getId()).toUri();
        return ResponseEntity.created(uri).body(response);
    }

    @PutMapping(value = {"/{id}"}, consumes = MediaType.APPLICATION_JSON_VALUE)
    @Operation(summary = "Modifica o cadastro de um profissional existente")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Cadastro do profissional atualizado com sucesso"),
            @ApiResponse(responseCode = "422", description = "Dados inválidos"),
            @ApiResponse(responseCode = "400", description = "Parâmetros inválidos"),
            @ApiResponse(responseCode = "500", description = "Erro ao atualizar o cadastro do profissional")
    })
    public ResponseEntity<ProfissionalResponse> atualizaprofissional(@PathVariable String id, @RequestBody ProfissionalRequest request) {
        ProfissionalResponse response = service.atualizaProfissional(id, request);
        return ResponseEntity.ok(response);
    }

    @DeleteMapping(value = {"/{id}"})
    @Operation(summary = "Remove um profissional existente")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Profissional removido com sucesso"),
            @ApiResponse(responseCode = "422", description = "Dados inválidos"),
            @ApiResponse(responseCode = "400", description = "Parâmetros inválidos"),
            @ApiResponse(responseCode = "500", description = "Erro ao remover o profissional")
    })
    public ResponseEntity<Void> deletaProfissional(@PathVariable String id) {
        service.deletaProfissional(id);
        return ResponseEntity.noContent().build();
    }
}
