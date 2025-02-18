package com.heniojunior.salaodebeleza.api.controllers;

import com.heniojunior.salaodebeleza.api.dtos.ProdutoDto;
import com.heniojunior.salaodebeleza.api.entities.Produto;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/produtos", produces = {"application/json"})
@Tag(name = "salao-de-beleza")
public class ProdutoController {


    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    @Operation(summary = "Realiza o cadastro de um novo produto")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Produto cadastrado com sucesso"),
            @ApiResponse(responseCode = "422", description = "Dados inválidos"),
            @ApiResponse(responseCode = "400", description = "Parâmetros inválidos"),
            @ApiResponse(responseCode = "500", description = "Erro ao realizar o cadastro")
    })
    public ResponseEntity<String> novoProduto(@RequestBody ProdutoDto dto) {
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

    public ResponseEntity<String> atualizaproduto(@PathVariable int id, @RequestBody ProdutoDto dto) {

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
    public ResponseEntity<Void> deletaproduto(@PathVariable Long id) {

        return ResponseEntity.noContent().build();
    }

    private void copydtoToEntity(ProdutoDto dto, Produto produto) {
        produto.setNome(dto.getNome());
        produto.setTipo(dto.getTipo());
        produto.setValor(dto.getValor());
    }
}
