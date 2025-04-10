package com.ecommerce.service;

import com.ecommerce.dto.EstadoRequestDTO;
import com.ecommerce.dto.EstadoResponseDTO;
import com.ecommerce.model.Estado;
import com.ecommerce.repository.EstadoRepository;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.ws.rs.core.Response;

import java.util.List;
import java.util.stream.Collectors;

@ApplicationScoped
public class EstadoServiceImpl implements EstadoService {

    @Inject
    EstadoRepository repository;

    @Override
    public Response listarTodos() {
        List<EstadoResponseDTO> estados = repository.listAll()
            .stream().map(EstadoResponseDTO::fromEntity).collect(Collectors.toList());
        return Response.ok(estados).build();
    }

    @Override
    public Response buscarPorId(Long id) {
        Estado e = repository.findById(id);
        if (e == null) return Response.status(Response.Status.NOT_FOUND).build();
        return Response.ok(EstadoResponseDTO.fromEntity(e)).build();
    }

    @Override
    public Response buscarPorSigla(String sigla) {
        Estado e = repository.buscarPorSigla(sigla);
        if (e == null) return Response.status(Response.Status.NOT_FOUND).build();
        return Response.ok(EstadoResponseDTO.fromEntity(e)).build();
    }

    @Override
    public void salvar(EstadoRequestDTO dto) {
        Estado e = new Estado();
        e.setNome(dto.nome());
        e.setSigla(dto.sigla());
        repository.persist(e);
    }

    @Override
    public void atualizar(Long id, EstadoRequestDTO dto) {
        Estado e = repository.findById(id);
        e.setNome(dto.nome());
        e.setSigla(dto.sigla());
    }

    @Override
    public void deletar(Long id) {
        repository.deleteById(id);
    }
}
