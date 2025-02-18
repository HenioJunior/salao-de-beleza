package com.heniojunior.salaodebeleza.api.entities;

import com.heniojunior.salaodebeleza.api.enums.TipoProduto;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.UUID;

@Document(collation = "produto_entity")
public class Produto {

    @Id
    private String id;
    private String nome;
    private double valor;
    private TipoProduto tipo;

    public Produto(String nome, double valor, TipoProduto tipo) {
        this.id = UUID.randomUUID().toString();
        this.nome = nome;
        this.valor = valor;
        this.tipo = tipo;
    }

    public Produto() {

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
