package com.heniojunior.salaodebeleza.api.entities;

import com.heniojunior.salaodebeleza.api.enums.TipoServico;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.UUID;

@Document(collection = "servicos")
public class Servico {

    @Id
    private String id;
    private double valor;
    private TipoServico tipo;

    public Servico(double valor, TipoServico tipo) {
        this.id = UUID.randomUUID().toString();
        this.valor = valor;
        this.tipo = tipo;
    }

    public Servico() {

    }

    public String getId() {
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
