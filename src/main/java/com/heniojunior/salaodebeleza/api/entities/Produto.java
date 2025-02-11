package com.heniojunior.salaodebeleza.api.entities;

import com.heniojunior.salaodebeleza.api.enums.TipoProduto;
import com.heniojunior.salaodebeleza.api.enums.TipoServico;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Produto {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nome;
    private double valor;
    private TipoProduto tipo;

    public Produto(String nome, double valor, TipoProduto tipo) {
        this.nome = nome;
        this.valor = valor;
        this.tipo = tipo;
    }

    public Produto() {

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
}
