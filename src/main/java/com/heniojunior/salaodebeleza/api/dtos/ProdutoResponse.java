package com.heniojunior.salaodebeleza.api.dtos;

import com.heniojunior.salaodebeleza.api.entities.Produto;
import com.heniojunior.salaodebeleza.api.enums.TipoProduto;

public class ProdutoResponse {

    private String id;
    private String nome;
    private double valor;
    private TipoProduto tipo;

    public ProdutoResponse(Produto produto) {
        this.id = produto.getId();
        this.nome = produto.getNome();
        this.valor = produto.getValor();
        this.tipo = produto.getTipo();
    }

    public String getId() {
        return id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public double getValor() {
        return valor;
    }

    public void setValor(double valor) {
        this.valor = valor;
    }

    public TipoProduto getTipo() {
        return tipo;
    }

    public void setTipo(TipoProduto tipo) {
        this.tipo = tipo;
    }

    public Produto toModel() {
        return new Produto(nome, valor, tipo);
    }
}
