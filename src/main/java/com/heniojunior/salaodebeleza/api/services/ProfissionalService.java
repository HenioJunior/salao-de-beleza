package com.heniojunior.salaodebeleza.api.services;

import com.heniojunior.salaodebeleza.api.dtos.ProfissionalRequest;
import com.heniojunior.salaodebeleza.api.dtos.ProfissionalResponse;
import com.heniojunior.salaodebeleza.api.entities.Profissional;
import com.heniojunior.salaodebeleza.api.repositories.ProfissionalRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class ProfissionalService {

    @Autowired
    private ProfissionalRepository repository;

    @Transactional(readOnly = true)
    public ProfissionalResponse buscaPorId(String id) {
        Profissional profissional = repository.findById(id).orElseThrow(
                () -> new RuntimeException("Recurso não encontrado"));
        return new ProfissionalResponse(profissional);
    }

    @Transactional(readOnly = true)
    public Page<ProfissionalResponse> buscaTodos(Pageable pageable) {
        Page<Profissional> result = repository.findAll(pageable);
        return result.map(ProfissionalResponse::new);
    }

    public ProfissionalResponse novoProfissional(ProfissionalRequest request) {
        Profissional profissional = new Profissional(request.getNome(), request.getCpf(),
                request.getEmail(), request.getTelefone());
        repository.save(profissional);
        return new ProfissionalResponse(profissional);
    }

    public ProfissionalResponse atualizaProfissional(String id, ProfissionalRequest request) {
        Profissional profissional = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Profissional não localizado"));
        profissional.setNome(request.getNome());
        profissional.setCpf(request.getCpf());
        profissional.setEmail(request.getEmail());
        profissional.setTelefone(request.getTelefone());
        repository.save(profissional);
        return new ProfissionalResponse(profissional);
    }

    @Transactional
    public void deletaProfissional(String id) {
        if(!repository.existsById(id)) {
            throw new RuntimeException("Recurso não encontrado");
        }
        try {
            repository.deleteById(id);
        } catch (DataIntegrityViolationException e) {
            throw new RuntimeException("Falha de integridade referencial");
        }
    }
}

