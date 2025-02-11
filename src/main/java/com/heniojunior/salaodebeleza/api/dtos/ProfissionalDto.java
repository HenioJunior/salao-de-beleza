package com.heniojunior.salaodebeleza.api.dtos;

import com.heniojunior.salaodebeleza.api.entities.Profissional;

public class ProfissionalDto {

    private String nome;
    private String cpf;
    private String email;
    private String telefone;

    public ProfissionalDto(String nome, String cpf, String email, String telefone) {
        this.nome = nome;
        this.cpf = cpf;
        this.email = email;
        this.telefone = telefone;
    }

    public ProfissionalDto() {

    }

    public ProfissionalDto(ProfissionalDto profissional) {
        this.nome = profissional.getNome();
        this.cpf = profissional.getCpf();
        this.email = profissional.getEmail();
        this.telefone = profissional.getTelefone();
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
