package com.heniojunior.salaodebeleza.api.controllers;

import com.heniojunior.salaodebeleza.api.dtos.ProdutoDto;
import com.heniojunior.salaodebeleza.api.dtos.ServicoRequest;
import com.heniojunior.salaodebeleza.api.entities.Produto;
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
@RequestMapping(value = "/produtos", produces = {"application/json"})
@Tag(name = "salao-de-beleza")
public class ProdutoController {

    @PersistenceContext
    private EntityManager manager;

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    @Operation(summary = "Realiza o cadastro de um novo produto")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Produto cadastrado com sucesso"),
            @ApiResponse(responseCode = "422", description = "Dados inválidos"),
            @ApiResponse(responseCode = "400", description = "Parâmetros inválidos"),
            @ApiResponse(responseCode = "500", description = "Erro ao realizar o cadastro")
    })
    @Transactional
    public ResponseEntity<String> novoProduto(@RequestBody ProdutoDto dto) {
        Produto produto = dto.toModel();
        manager.persist(produto);
        return ResponseEntity.ok("produto cadastrado com sucesso.");
    }

    @PutMapping(value = {"/{id}"}, consumes = MediaType.APPLICATION_JSON_VALUE)
    @Operation(summary = "Modifica o cadastro de um produto existente")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Cadastro do produto atualizado com sucesso"),
            @ApiResponse(responseCode = "422", description = "Dados inválidos"),
            @ApiResponse(responseCode = "400", description = "Parâmetros inválidos"),
            @ApiResponse(responseCode = "500", description = "Erro ao atualizar o cadastro do produto")
    })
    @Transactional
    public ResponseEntity<String> atualizaproduto(@PathVariable int id, @RequestBody ProdutoDto dto) {
        Produto produto = manager.find(Produto.class, id);
        copydtoToEntity(dto, produto);
        manager.persist(produto);
        return ResponseEntity.ok("Produto atualizado com sucesso.");
    }

    @DeleteMapping(value = {"/{id}"}, consumes = MediaType.APPLICATION_JSON_VALUE)
    @Operation(summary = "Remove um produto existente")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Produto removido com sucesso"),
            @ApiResponse(responseCode = "422", description = "Dados inválidos"),
            @ApiResponse(responseCode = "400", description = "Parâmetros inválidos"),
            @ApiResponse(responseCode = "500", description = "Erro ao remover o produto")
    })
    @Transactional
    public ResponseEntity<Void> deletaproduto(@PathVariable Long id) {
        Produto produto = manager.find(Produto.class, id);
        manager.remove(produto);
        return ResponseEntity.noContent().build();
    }

    private void copydtoToEntity(ProdutoDto dto, Produto produto) {
        produto.setNome(dto.getNome());
        produto.setTipo(dto.getTipo());
        produto.setValor(dto.getValor());
    }
}
