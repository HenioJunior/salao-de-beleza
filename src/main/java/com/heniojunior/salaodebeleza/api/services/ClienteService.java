package com.heniojunior.salaodebeleza.api.services;

import com.heniojunior.salaodebeleza.api.dtos.ClienteRequest;
import com.heniojunior.salaodebeleza.api.dtos.ClienteResponse;
import com.heniojunior.salaodebeleza.api.entities.Cliente;
import com.heniojunior.salaodebeleza.api.repositories.ClienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

@Service
public class ClienteService {

    @Autowired
    private ClienteRepository repository;


    public ClienteResponse novoCliente(ClienteRequest dto) {
        Cliente cliente = new Cliente(dto.getNome(), dto.getCpf(),
                dto.getEmail(), dto.getTelefone());
        repository.save(cliente);
        return new ClienteResponse(cliente);
    }

    public ClienteResponse AtualizaCliente(Long id, ClienteRequest request) {
        Cliente cliente = repository.findById(id.toString())
                .orElseThrow(() -> new RuntimeException("Cliente não localizado"));
        cliente.setNome(request.getNome());
        cliente.setCpf(request.getCpf());
        cliente.setEmail(request.getEmail());
        cliente.setTelefone(request.getTelefone());
        repository.save(cliente);
        return new ClienteResponse(cliente);
    }

    public void deletaCliente(Long id) {
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

