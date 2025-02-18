package com.heniojunior.salaodebeleza.api.services;

import com.heniojunior.salaodebeleza.api.dtos.ClienteRequest;
import com.heniojunior.salaodebeleza.api.dtos.ClienteResponse;
import com.heniojunior.salaodebeleza.api.dtos.ProdutoResponse;
import com.heniojunior.salaodebeleza.api.entities.Cliente;
import com.heniojunior.salaodebeleza.api.entities.Produto;
import com.heniojunior.salaodebeleza.api.repositories.ClienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class ClienteService {

    @Autowired
    private ClienteRepository repository;

    @Transactional(readOnly = true)
    public ClienteResponse buscaPorId(String id) {
        Cliente cliente = repository.findById(id).orElseThrow(
                () -> new RuntimeException("Recurso não encontrado"));
        return new ClienteResponse(cliente);
    }

    @Transactional(readOnly = true)
    public Page<ClienteResponse> buscaTodos(Pageable pageable) {
        Page<Cliente> result = repository.findAll(pageable);
        return result.map(ClienteResponse::new);
    }

    public ClienteResponse novoCliente(ClienteRequest request) {
        Cliente cliente = new Cliente(request.getNome(), request.getCpf(),
                request.getEmail(), request.getTelefone());
        repository.save(cliente);
        return new ClienteResponse(cliente);
    }

    public ClienteResponse AtualizaCliente(String id, ClienteRequest request) {
        Cliente cliente = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Cliente não localizado"));
        cliente.setNome(request.getNome());
        cliente.setCpf(request.getCpf());
        cliente.setEmail(request.getEmail());
        cliente.setTelefone(request.getTelefone());
        repository.save(cliente);
        return new ClienteResponse(cliente);
    }

    public void deletaCliente(String id) {
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

