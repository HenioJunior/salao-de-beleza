package com.heniojunior.salaodebeleza.api.dtos;

import com.heniojunior.salaodebeleza.api.entities.Cliente;
import jakarta.persistence.EntityManager;

public class ClienteRequest {

    private String nome;
    private String cpf;
    private String email;
    private String telefone;

    public ClienteRequest(String nome, String cpf, String email, String telefone) {
        this.nome = nome;
        this.cpf = cpf;
        this.email = email;
        this.telefone = telefone;
    }

    public String getNome() {
        return nome;
    }

    public String getCpf() {
        return cpf;
    }

    public String getEmail() {
        return email;
    }

    public String getTelefone() {
        return telefone;
    }

    public Cliente toModel() {
        return new Cliente(nome, cpf, email, telefone);
    }
}
