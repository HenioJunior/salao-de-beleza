package com.heniojunior.salaodebeleza.api.services;


import com.heniojunior.salaodebeleza.api.dtos.ServicoRequest;
import com.heniojunior.salaodebeleza.api.dtos.ServicoResponse;
import com.heniojunior.salaodebeleza.api.entities.Servico;
import com.heniojunior.salaodebeleza.api.repositories.ServicoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

@Service
public class ServicoService {

    @Autowired
    private ServicoRepository repository;


    public ServicoResponse novoServico(ServicoRequest request) {
        Servico servico = new Servico(request.getValor(), request.getTipo());
        repository.save(servico);
        return new ServicoResponse(servico);
    }

    public ServicoResponse atualizaServico(Long id, ServicoRequest request) {
        Servico servico = repository.findById(id.toString())
                .orElseThrow(() -> new RuntimeException("Servico não localizado"));
        servico.setValor(request.getValor());
        servico.setTipo(request.getTipo());
        repository.save(servico);
        return new ServicoResponse(servico);
    }

    public void deletaServico(Long id) {
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

