package com.ecommerce.service;

import com.ecommerce.dto.FornecedorRequestDTO;
import com.ecommerce.dto.FornecedorResponseDTO;
import com.ecommerce.model.Fornecedor;
import com.ecommerce.repository.FornecedorRepository;
import com.ecommerce.repository.MarcaRepository;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.ws.rs.core.Response;

import java.util.List;
import java.util.stream.Collectors;

@ApplicationScoped
public class FornecedorServiceImpl implements FornecedorService {

    @Inject
    FornecedorRepository repository;
    @Inject 
    MarcaRepository marcaRepository;

    @Override
    public Response listarTodos() {
        List<FornecedorResponseDTO> lista = repository.listAll()
            .stream().map(FornecedorResponseDTO::fromEntity).collect(Collectors.toList());
        return Response.ok(lista).build();
    }

    @Override
    public Response buscarPorId(Long id) {
        Fornecedor f = repository.findById(id);
        if (f == null) return Response.status(Response.Status.NOT_FOUND).build();
        return Response.ok(FornecedorResponseDTO.fromEntity(f)).build();
    }

    @Override
    public Response buscarPorNome(String nome) {
        Fornecedor f = repository.buscarPorNome(nome);
        if (f == null) return Response.status(Response.Status.NOT_FOUND).build();
        return Response.ok(FornecedorResponseDTO.fromEntity(f)).build();
    }

    @Override
    public Response buscarPorCnpj(String cnpj) {
        Fornecedor f = repository.buscarPorCnpj(cnpj);
        if (f == null) return Response.status(Response.Status.NOT_FOUND).build();
        return Response.ok(FornecedorResponseDTO.fromEntity(f)).build();
    }

    @Override
    public Response buscarPorTelefone(String telefone) {
        Fornecedor f = repository.buscarPorTelefone(telefone);
        if (f == null) return Response.status(Response.Status.NOT_FOUND).build();
        return Response.ok(FornecedorResponseDTO.fromEntity(f)).build();
    }

    @Override
    public void salvar(FornecedorRequestDTO dto) {
        Fornecedor fornecedor = new Fornecedor();
        fornecedor.setNome(dto.nome());
        fornecedor.setCnpj(dto.cnpj());
        fornecedor.setTelefone(dto.telefone());
        if (dto.marcasIds() != null) {
            fornecedor.setMarcas(dto.marcasIds().stream()
                .map(marcaRepository::findById)
                .toList());
        }    
        repository.persist(fornecedor);
    }

    @Override
    public void atualizar(Long id, FornecedorRequestDTO dto) {
        Fornecedor f = repository.findById(id);
        f.setNome(dto.nome());
        f.setCnpj(dto.cnpj());
        f.setTelefone(dto.telefone());
        if (dto.marcasIds() != null) {
            f.setMarcas(dto.marcasIds().stream()
                .map(marcaRepository::findById)
                .toList());
        }
    }

    @Override
    public void deletar(Long id) {
        repository.deleteById(id);
    }
}
