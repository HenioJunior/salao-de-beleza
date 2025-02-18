package com.heniojunior.salaodebeleza.api.services;

import com.heniojunior.salaodebeleza.api.dtos.ProfissionalRequest;
import com.heniojunior.salaodebeleza.api.dtos.ProfissionalResponse;
import com.heniojunior.salaodebeleza.api.entities.Profissional;
import com.heniojunior.salaodebeleza.api.repositories.ProfissionalRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

@Service
public class ProfissionalService {

    @Autowired
    private ProfissionalRepository repository;


    public ProfissionalResponse novoProfissional(ProfissionalRequest request) {
        Profissional profissional = new Profissional(request.getNome(), request.getCpf(),
                request.getEmail(), request.getTelefone());
        repository.save(profissional);
        return new ProfissionalResponse(profissional);
    }

    public ProfissionalResponse atualizaProfissional(Long id, ProfissionalRequest request) {
        Profissional profissional = repository.findById(id.toString())
                .orElseThrow(() -> new RuntimeException("Profissional não localizado"));
        profissional.setNome(request.getNome());
        profissional.setCpf(request.getCpf());
        profissional.setEmail(request.getEmail());
        profissional.setTelefone(request.getTelefone());
        repository.save(profissional);
        return new ProfissionalResponse(profissional);
    }

    public void deletaProfissional(Long id) {
        if(!repository.existsById(id.toString())) {
            throw new RuntimeException("Recurso não encontrado");
        }
        try {
            repository.deleteById(id.toString());
        } catch (DataIntegrityViolationException e) {
            throw new RuntimeException("Falha de integridade referencial");
        }
    }
}

