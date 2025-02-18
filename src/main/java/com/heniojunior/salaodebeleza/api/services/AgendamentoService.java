package com.heniojunior.salaodebeleza.api.services;

import com.heniojunior.salaodebeleza.api.dtos.AgendamentoRequest;
import com.heniojunior.salaodebeleza.api.dtos.AgendamentoResponse;
import com.heniojunior.salaodebeleza.api.dtos.ProdutoResponse;
import com.heniojunior.salaodebeleza.api.entities.*;
import com.heniojunior.salaodebeleza.api.repositories.AgendamentoRepository;
import com.heniojunior.salaodebeleza.api.repositories.ClienteRepository;
import com.heniojunior.salaodebeleza.api.repositories.ProfissionalRepository;
import com.heniojunior.salaodebeleza.api.repositories.ServicoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class AgendamentoService {


    @Autowired
    private AgendamentoRepository agendamentoRepository;
    @Autowired
    private ClienteRepository clienteRepository;
    @Autowired
    private ProfissionalRepository profissionalRepository;
    @Autowired
    private ServicoRepository servicoRepository;


    @Transactional(readOnly = true)
    public AgendamentoResponse buscaPorId(String id) {
        Agendamento agendamento = agendamentoRepository.findById(id).orElseThrow(
                () -> new RuntimeException("Recurso não encontrado"));
        return new AgendamentoResponse(agendamento);
    }

    @Transactional(readOnly = true)
    public Page<AgendamentoResponse> buscaTodos(Pageable pageable) {
        Page<Agendamento> result = agendamentoRepository.findAll(pageable);
        return result.map(AgendamentoResponse::new);
    }

    public AgendamentoResponse novoAgendamento(AgendamentoRequest request) {
        Cliente cliente = getCliente(request.getClienteId());
        Profissional profissional = getProfissional(request.getProfissionalId());
        Servico servico = getServico(request.getServicoId());

        Agendamento agendamento = new Agendamento(request.getHorario(), cliente, profissional, servico);
        agendamentoRepository.save(agendamento);
        return new AgendamentoResponse(agendamento);

    }

    public AgendamentoResponse atualizaAgendamento(String id, AgendamentoRequest request) {
        Agendamento agendamento = agendamentoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Agendamento não localizado"));
        agendamento.setHorario(request.getHorario());
        agendamento.setCliente(getCliente(request.getClienteId()));
        agendamento.setProfissional(getProfissional(request.getProfissionalId()));
        agendamento.setServico(getServico(request.getServicoId()));
        agendamentoRepository.save(agendamento);
        return new AgendamentoResponse(agendamento);
    }

    public void deletaAgendamento(String id) {
        if(!agendamentoRepository.existsById(id)) {
            throw new RuntimeException("Recurso não encontrado");
        }
        try {
            agendamentoRepository.deleteById(id);
        } catch (DataIntegrityViolationException e) {
            throw new RuntimeException("Falha de integridade referencial");
        }
    }

    private Cliente getCliente(String id) {
        return clienteRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Cliente não localizado"));
    }

    private Servico getServico(String id) {
        return servicoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Servico não localizado"));
    }

    private Profissional getProfissional(String id) {
        return profissionalRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Profissional não localizado"));
    }
}

