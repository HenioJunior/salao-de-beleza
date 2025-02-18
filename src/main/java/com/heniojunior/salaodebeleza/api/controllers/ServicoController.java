package com.heniojunior.salaodebeleza.api.controllers;

import com.heniojunior.salaodebeleza.api.dtos.ServicoRequest;
import com.heniojunior.salaodebeleza.api.dtos.ServicoResponse;
import com.heniojunior.salaodebeleza.api.services.ServicoService;
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
@RequestMapping(value = "/servicos", produces = {"application/json"})
@Tag(name = "salao-de-beleza")
public class ServicoController {

    @Autowired
    private ServicoService service;

    @GetMapping("/{id}")
    @Operation(summary = "Realiza a busca de um serviço por id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Ok"),

    })
    public ResponseEntity<ServicoResponse> findById(@PathVariable String id) {
        ServicoResponse response =  service.buscaPorId(id);
        return ResponseEntity.ok(response);
    }

    @GetMapping
    @Operation(summary = "Realiza a busca de todos serviços")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "ok"),

    })
    public ResponseEntity<Page<ServicoResponse>>  findAll(Pageable pageable) {
        Page<ServicoResponse> response =  service.buscaTodos(pageable);
        return ResponseEntity.ok(response);
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    @Operation(summary = "Realiza o cadastro de um novo servico")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Servico cadastrado com sucesso"),
            @ApiResponse(responseCode = "422", description = "Dados inválidos"),
            @ApiResponse(responseCode = "400", description = "Parâmetros inválidos"),
            @ApiResponse(responseCode = "500", description = "Erro ao realizar o cadastro")
    })
    public ResponseEntity<ServicoResponse> novoServico(@RequestBody ServicoRequest request) {
        ServicoResponse response = service.novoServico(request);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}").buildAndExpand(response.getId()).toUri();
        return ResponseEntity.created(uri).body(response);
    }

    @PutMapping(value = {"/{id}"}, consumes = MediaType.APPLICATION_JSON_VALUE)
    @Operation(summary = "Modifica o cadastro de um servico existente")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Cadastro do servico atualizado com sucesso"),
            @ApiResponse(responseCode = "422", description = "Dados inválidos"),
            @ApiResponse(responseCode = "400", description = "Parâmetros inválidos"),
            @ApiResponse(responseCode = "500", description = "Erro ao atualizar o cadastro do servico")
    })
    public ResponseEntity<ServicoResponse> atualizaServico(@PathVariable String id, @RequestBody ServicoRequest request) {
        ServicoResponse response = service.atualizaServico(id, request);
        return ResponseEntity.ok(response);
    }

    @DeleteMapping(value = {"/{id}"})
    @Operation(summary = "Remove um servico existente")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Servico removido com sucesso"),
            @ApiResponse(responseCode = "422", description = "Dados inválidos"),
            @ApiResponse(responseCode = "400", description = "Parâmetros inválidos"),
            @ApiResponse(responseCode = "500", description = "Erro ao remover o servico")
    })
    public ResponseEntity<Void> deletaServico(@PathVariable String id) {
        service.deletaServico(id);
        return ResponseEntity.noContent().build();
    }
}
