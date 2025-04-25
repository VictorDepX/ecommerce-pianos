package com.ecommerce.service;

import com.ecommerce.dto.EnderecoRequestDTO;
import com.ecommerce.dto.EnderecoResponseDTO;
import com.ecommerce.model.Endereco;
import com.ecommerce.model.Estado;
import com.ecommerce.model.Municipio;
import com.ecommerce.repository.EnderecoRepository;
import com.ecommerce.repository.EstadoRepository;
import com.ecommerce.repository.MunicipioRepository;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import jakarta.ws.rs.core.Response;

import java.util.List;
import java.util.stream.Collectors;

@ApplicationScoped
public class EnderecoServiceImpl implements EnderecoService {

    @Inject EnderecoRepository repository;
    @Inject EstadoRepository estadoRepository;
    @Inject MunicipioRepository municipioRepository;

    public Response listarTodos() {
        List<EnderecoResponseDTO> list = repository.listAll()
            .stream().map(EnderecoResponseDTO::fromEntity).collect(Collectors.toList());
        return Response.ok(list).build();
    }

    public Response buscarPorId(Long id) {
        Endereco endereco = repository.findById(id);
        return Response.ok(EnderecoResponseDTO.fromEntity(endereco)).build();
    }

    @Transactional
    public void salvar(EnderecoRequestDTO dto) {
        Endereco endereco = new Endereco() {}; // classe abstrata sendo instanciada anonimamente
        endereco.setRua(dto.rua());
        endereco.setNumero(dto.numero());
        endereco.setBairro(dto.bairro());
        endereco.setCidade(dto.cidade());
        endereco.setCep(dto.cep());
        endereco.setTipoEndereco(dto.tipoEndereco());
        Estado estado = estadoRepository.findById(dto.estadoId());
        Municipio municipio = municipioRepository.findById(dto.municipioId());
        endereco.setEstado(estado);
        endereco.setMunicipio(municipio);
        repository.persist(endereco);
    }

    @Transactional
    public void atualizar(Long id, EnderecoRequestDTO dto) {
        Endereco endereco = repository.findById(id);
        endereco.setRua(dto.rua());
        endereco.setNumero(dto.numero());
        endereco.setBairro(dto.bairro());
        endereco.setCidade(dto.cidade());
        endereco.setCep(dto.cep());
        endereco.setTipoEndereco(dto.tipoEndereco());
        Estado estado = estadoRepository.findById(dto.estadoId());
        Municipio municipio = municipioRepository.findById(dto.municipioId());
        endereco.setEstado(estado);
        endereco.setMunicipio(municipio);
    }

    @Transactional
    public void deletar(Long id) {
        repository.deleteById(id);
    }
}
