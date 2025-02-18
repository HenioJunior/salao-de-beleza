package com.heniojunior.salaodebeleza.api.dtos;

import com.heniojunior.salaodebeleza.api.enums.TipoProduto;

public class ProdutoRequest {

    private String id;
    private String nome;
    private double valor;
    private TipoProduto tipo;

    public ProdutoRequest(String nome, double valor, TipoProduto tipo) {
        this.nome = nome;
        this.valor = valor;
        this.tipo = tipo;
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
}
