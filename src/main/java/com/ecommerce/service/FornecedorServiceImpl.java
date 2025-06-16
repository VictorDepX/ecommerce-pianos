package com.ecommerce.service;

import com.ecommerce.dto.FornecedorRequestDTO;
import com.ecommerce.dto.FornecedorResponseDTO;
import com.ecommerce.model.Fornecedor;
import com.ecommerce.repository.FornecedorRepository;
import com.ecommerce.repository.MarcaRepository;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import jakarta.ws.rs.core.Response;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@ApplicationScoped
public class FornecedorServiceImpl implements FornecedorService {

    @Inject
    FornecedorRepository repository;
    @Inject 
    MarcaRepository marcaRepository;

    @Override
    public List<FornecedorResponseDTO> listarTodos() {
        List<FornecedorResponseDTO> fornecedores = repository.listAll()
            .stream()
            .map(FornecedorResponseDTO::fromEntity)
            .collect(Collectors.toList());
        return fornecedores;
    }

    @Override
    public Response buscarPorId(Long id) {
        Fornecedor f = repository.findById(id);
        if (f == null) return Response.status(Response.Status.NOT_FOUND).build();
        return Response.ok(FornecedorResponseDTO.fromEntity(f)).build();    
    }

    @Override
    public FornecedorResponseDTO buscarPorNome(String nome) {
        Fornecedor f = repository.buscarPorNome(nome);
        return FornecedorResponseDTO.fromEntity(f);
    }

    @Override
    public FornecedorResponseDTO buscarPorCnpj(String cnpj) {
        Fornecedor f = repository.buscarPorCnpj(cnpj);
        return FornecedorResponseDTO.fromEntity(f);   
    }

    @Override
    public FornecedorResponseDTO buscarPorTelefone(String telefone) {
        Fornecedor f = repository.buscarPorTelefone(telefone);
        return FornecedorResponseDTO.fromEntity(f);    
    }

    @Override
    @Transactional
    public FornecedorResponseDTO salvar(FornecedorRequestDTO dto) {
        
        Fornecedor fornecedor = new Fornecedor();
        fornecedor.setNome(dto.nome());
        fornecedor.setCnpj(dto.cnpj());
        fornecedor.setTelefone(dto.telefone());
        fornecedor.setMarcas(dto.marcasIds().stream()
            .map(marcaRepository::findById)
            .filter(Objects::nonNull)
            .toList());
        repository.persist(fornecedor);

        return FornecedorResponseDTO.fromEntity(fornecedor);
    }

    @Override
    @Transactional
    public void atualizar(Long id, FornecedorRequestDTO dto) {
        Fornecedor fornecedor = repository.findById(id);
        fornecedor.setNome(dto.nome());
        fornecedor.setCnpj(dto.cnpj());
        fornecedor.setTelefone(dto.telefone());
        fornecedor.setMarcas(dto.marcasIds().stream()
            .map(marcaRepository::findById)
            .filter(Objects::nonNull)
            .toList());
    }

    @Override
    @Transactional
    public void deletar(Long id) {
        repository.deleteById(id);
    }
}
