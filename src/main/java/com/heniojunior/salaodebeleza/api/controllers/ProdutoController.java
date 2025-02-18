package com.heniojunior.salaodebeleza.api.controllers;

import com.heniojunior.salaodebeleza.api.dtos.ProdutoRequest;
import com.heniojunior.salaodebeleza.api.dtos.ProdutoResponse;
import com.heniojunior.salaodebeleza.api.services.ProdutoService;
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
@RequestMapping(value = "/produtos", produces = {"application/json"})
@Tag(name = "Produto")
public class ProdutoController {

    @Autowired
    private ProdutoService service;

    @GetMapping("/{id}")
    @Operation(summary = "Realiza a busca de um produto por id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Ok"),

    })
    public ResponseEntity<ProdutoResponse> findById(@PathVariable String id) {
        ProdutoResponse response =  service.buscaPorId(id);
        return ResponseEntity.ok(response);
    }

    @GetMapping
    @Operation(summary = "Realiza a busca de todos os produtos")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "ok"),

    })
    public ResponseEntity<Page<ProdutoResponse>>  findAll(Pageable pageable) {
        Page<ProdutoResponse> response =  service.buscaTodos(pageable);
        return ResponseEntity.ok(response);
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    @Operation(summary = "Realiza o cadastro de um novo produto")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Produto cadastrado com sucesso"),
            @ApiResponse(responseCode = "422", description = "Dados inválidos"),
            @ApiResponse(responseCode = "400", description = "Parâmetros inválidos"),
            @ApiResponse(responseCode = "500", description = "Erro ao realizar o cadastro")
    })
    public ResponseEntity<ProdutoResponse> novoProduto(@RequestBody ProdutoRequest request) {
        ProdutoResponse response = service.novoProduto(request);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(response.getId()).toUri();
       return ResponseEntity.created(uri).body(response);
    }
    @PutMapping(value = {"/{id}"}, consumes = MediaType.APPLICATION_JSON_VALUE)
    @Operation(summary = "Modifica o cadastro de um produto existente")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Cadastro do produto atualizado com sucesso"),
            @ApiResponse(responseCode = "422", description = "Dados inválidos"),
            @ApiResponse(responseCode = "400", description = "Parâmetros inválidos"),
            @ApiResponse(responseCode = "500", description = "Erro ao atualizar o cadastro do produto")
    })
   public ResponseEntity<ProdutoResponse> atualizaproduto(@PathVariable String id, @RequestBody ProdutoRequest request) {
        ProdutoResponse response = service.atualizaProduto(id, request);
        return ResponseEntity.ok(response);
    }

    @DeleteMapping(value = {"/{id}"})
    @Operation(summary = "Remove um produto existente")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Produto removido com sucesso"),
            @ApiResponse(responseCode = "422", description = "Dados inválidos"),
            @ApiResponse(responseCode = "400", description = "Parâmetros inválidos"),
            @ApiResponse(responseCode = "500", description = "Erro ao remover o produto")
    })
    public ResponseEntity<Void> deletaproduto(@PathVariable String id) {
        service.deletaProduto(id);
        return ResponseEntity.noContent().build();
    }
}
