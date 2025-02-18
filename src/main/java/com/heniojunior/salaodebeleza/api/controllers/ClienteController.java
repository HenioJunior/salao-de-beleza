package com.heniojunior.salaodebeleza.api.controllers;

import com.heniojunior.salaodebeleza.api.dtos.ClienteRequest;
import com.heniojunior.salaodebeleza.api.dtos.ClienteResponse;
import com.heniojunior.salaodebeleza.api.dtos.ProdutoResponse;
import com.heniojunior.salaodebeleza.api.services.ClienteService;
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
@RequestMapping(value = "/clientes", produces = {"application/json"})
@Tag(name = "salao-de-beleza")
public class ClienteController {

    @Autowired
    private ClienteService service;

    @GetMapping("/{id}")
    @Operation(summary = "Realiza a busca de um cliente por id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Ok"),

    })
    public ResponseEntity<ClienteResponse> findById(@PathVariable String id) {
        ClienteResponse  response =  service.buscaPorId(id);
        return ResponseEntity.ok(response);
    }

    @GetMapping
    @Operation(summary = "Realiza a busca de todos os clientes")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "ok"),

    })
    public ResponseEntity<Page<ClienteResponse>>  findAll(Pageable pageable) {
        Page<ClienteResponse> response =  service.buscaTodos(pageable);
        return ResponseEntity.ok(response);
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    @Operation(summary = "Realiza o cadastro de um novo cliente")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Cliente cadastrado com sucesso"),
            @ApiResponse(responseCode = "422", description = "Dados inválidos"),
            @ApiResponse(responseCode = "400", description = "Parâmetros inválidos"),
            @ApiResponse(responseCode = "500", description = "Erro ao realizar o cadastro")
    })
    public ResponseEntity<ClienteResponse> novoCliente(@RequestBody ClienteRequest dto) {
        ClienteResponse response = service.novoCliente(dto);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
                .buildAndExpand(response.getId()).toUri();
        return ResponseEntity.created(uri).body(response);
    }

    @PutMapping(value = {"/{id}"}, consumes = MediaType.APPLICATION_JSON_VALUE)
    @Operation(summary = "Modifica o cadastro de um cliente existente")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Cadastro do cliente atualizado com sucesso"),
            @ApiResponse(responseCode = "422", description = "Dados inválidos"),
            @ApiResponse(responseCode = "400", description = "Parâmetros inválidos"),
            @ApiResponse(responseCode = "500", description = "Erro ao atualizar o cadastro do cliente")
    })
    public ResponseEntity<ClienteResponse> atualizaCliente(@PathVariable String id, @RequestBody ClienteRequest request) {
        ClienteResponse response = service.AtualizaCliente(id, request);
        return ResponseEntity.ok(response);
    }

    @DeleteMapping(value = {"/{id}"})
    @Operation(summary = "Remove um cliente existente")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Cliente removido com sucesso"),
            @ApiResponse(responseCode = "422", description = "Dados inválidos"),
            @ApiResponse(responseCode = "400", description = "Parâmetros inválidos"),
            @ApiResponse(responseCode = "500", description = "Erro ao remover o cliente")
    })
    public ResponseEntity<Void> delete(@PathVariable String id) {
        service.deletaCliente(id);
        return ResponseEntity.noContent().build();
    }
}
