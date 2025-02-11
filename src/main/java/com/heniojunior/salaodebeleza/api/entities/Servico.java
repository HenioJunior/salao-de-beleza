package com.heniojunior.salaodebeleza.api.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Servico {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private double valor;
    private TipoServico tipo;

    public Servico(double valor, TipoServico tipo) {
        this.valor = valor;
        this.tipo = tipo;
    }

    public Servico() {

    }

    public Servico(Servico servico) {
        this.valor = servico.valor;
        this.tipo = servico.tipo;
    }

    public Long getId() {
        return id;
    }

    public double getValor() {
        return valor;
    }

    public void setValor(double valor) {
        this.valor = valor;
    }

    public TipoServico getTipo() {
        return tipo;
    }

    public void setTipo(TipoServico tipo) {
        this.tipo = tipo;
    }
}
