package com.heniojunior.salaodebeleza.api.controllers;

import com.heniojunior.salaodebeleza.api.dtos.ClienteRequest;
import com.heniojunior.salaodebeleza.api.entities.Cliente;
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
@RequestMapping(value = "/clientes", produces = {"application/json"})
@Tag(name = "salao-de-beleza")
public class ClienteController {

    @PersistenceContext
    private EntityManager manager;

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    @Operation(summary = "Realiza o cadastro de um novo cliente")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Cliente cadastrado com sucesso"),
            @ApiResponse(responseCode = "422", description = "Dados inválidos"),
            @ApiResponse(responseCode = "400", description = "Parâmetros inválidos"),
            @ApiResponse(responseCode = "500", description = "Erro ao realizar o cadastro")
    })
    @Transactional
    public ResponseEntity<String> novoCliente(@RequestBody ClienteRequest request) {
        Cliente cliente = request.toModel();
        manager.persist(cliente);
        return ResponseEntity.ok("Cliente cadastrado com sucesso.");
    }

    @PutMapping(value = {"/{id}"}, consumes = MediaType.APPLICATION_JSON_VALUE)
    @Operation(summary = "Modifica o cadastro de um cliente existente")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Cadastro do cliente atualizado com sucesso"),
            @ApiResponse(responseCode = "422", description = "Dados inválidos"),
            @ApiResponse(responseCode = "400", description = "Parâmetros inválidos"),
            @ApiResponse(responseCode = "500", description = "Erro ao atualizar o cadastro do cliente")
    })
    @Transactional
    public ResponseEntity<String> atualizaCliente(@PathVariable int id, @RequestBody ClienteRequest request) {
        Cliente cliente = manager.find(Cliente.class, id);
        copydtoToEntity(request, cliente);
        manager.persist(cliente);
        return ResponseEntity.ok("Agendamento atualizado com sucesso.");
    }

    @DeleteMapping(value = {"/{id}"}, consumes = MediaType.APPLICATION_JSON_VALUE)
    @Operation(summary = "Remove um cliente existente")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Cliente removido com sucesso"),
            @ApiResponse(responseCode = "422", description = "Dados inválidos"),
            @ApiResponse(responseCode = "400", description = "Parâmetros inválidos"),
            @ApiResponse(responseCode = "500", description = "Erro ao remover o cliente")
    })
    @Transactional
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        Cliente cliente = manager.find(Cliente.class, id);
        manager.remove(cliente);
        return ResponseEntity.noContent().build();
    }

    private void copydtoToEntity(ClienteRequest request, Cliente cliente) {
        cliente.setNome(request.getNome());
        cliente.setCpf(request.getCpf());
        cliente.setEmail(request.getEmail());
        cliente.setTelefone(request.getTelefone());
    }
}
