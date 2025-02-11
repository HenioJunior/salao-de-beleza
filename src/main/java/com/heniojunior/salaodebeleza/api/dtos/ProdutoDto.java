package com.heniojunior.salaodebeleza.api.dtos;

import com.heniojunior.salaodebeleza.api.entities.Produto;
import com.heniojunior.salaodebeleza.api.enums.TipoProduto;

public class ProdutoDto {

    private Long id;
    private String nome;
    private double valor;
    private TipoProduto tipo;

    public ProdutoDto(String nome, double valor, TipoProduto tipo) {
        this.nome = nome;
        this.valor = valor;
        this.tipo = tipo;
    }

    public Long getId() {
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
