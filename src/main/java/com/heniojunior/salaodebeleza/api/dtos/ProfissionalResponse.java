package com.heniojunior.salaodebeleza.api.dtos;

import com.heniojunior.salaodebeleza.api.entities.Profissional;

public class ProfissionalResponse {


    private String id;
    private String nome;
    private String cpf;
    private String email;
    private String telefone;


    public ProfissionalResponse(Profissional profissional) {
        this.id = profissional.getId();
        this.nome = profissional.getNome();
        this.cpf = profissional.getCpf();
        this.email = profissional.getEmail();
        this.telefone = profissional.getTelefone();
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

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public Profissional toModel() {
        return new Profissional(nome, cpf, email, telefone);
    }
}
