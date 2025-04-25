package com.ecommerce.service;

import com.ecommerce.dto.MarcaRequestDTO;
import com.ecommerce.dto.MarcaResponseDTO;
import com.ecommerce.model.Marca;
import com.ecommerce.repository.MarcaRepository;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.ws.rs.core.Response;

import java.util.List;
import java.util.stream.Collectors;

@ApplicationScoped
public class MarcaServiceImpl implements MarcaService {

    @Inject MarcaRepository repository;

    @Override
    public Response listarTodos() {
        List<MarcaResponseDTO> list = repository.listAll().stream()
            .map(MarcaResponseDTO::fromEntity).collect(Collectors.toList());
        return Response.ok(list).build();
    }

    @Override
    public Response buscarPorId(Long id) {
        Marca m = repository.findById(id);
        if (m == null) return Response.status(Response.Status.NOT_FOUND).build();
        return Response.ok(MarcaResponseDTO.fromEntity(m)).build();
    }

    @Override
    public void salvar(MarcaRequestDTO dto) {
        Marca m = new Marca();
        m.setNome(dto.nome());
        m.setCnpj(dto.cnpj());
        repository.persist(m);
    }

    @Override
    public void atualizar(Long id, MarcaRequestDTO dto) {
        Marca m = repository.findById(id);
        m.setNome(dto.nome());
        m.setCnpj(dto.cnpj());
    }

    @Override
    public void deletar(Long id) {
        repository.deleteById(id);
    }
}
