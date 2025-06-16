package com.ecommerce.service;

import com.ecommerce.dto.EnderecoClienteRequestDTO;
import com.ecommerce.dto.EnderecoClienteResponseDTO;
import com.ecommerce.model.Cliente;
import com.ecommerce.model.EnderecoCliente;
import com.ecommerce.repository.ClienteRepository;
import com.ecommerce.repository.EnderecoClienteRepository;
import com.ecommerce.repository.EstadoRepository;
import com.ecommerce.repository.MunicipioRepository;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import jakarta.ws.rs.core.Response;

import java.util.List;

import org.eclipse.microprofile.jwt.JsonWebToken;

@ApplicationScoped
public class EnderecoClienteServiceImpl implements EnderecoClienteService {

    @Inject EnderecoClienteRepository repository;
    @Inject EstadoRepository estadoRepository;
    @Inject MunicipioRepository municipioRepository;
    @Inject ClienteRepository clienteRepository;
    @Inject JsonWebToken jwt;

    @Override
    public List<EnderecoClienteResponseDTO> buscarPorNome(String nome) {
        List<EnderecoCliente> endereco = repository.buscarPorNome(nome);
        return endereco.stream()
            .map(EnderecoClienteResponseDTO::fromEntity)
            .toList();
    }

    @Override
    public Response buscarPorId(Long id) {
        EnderecoCliente endereco = repository.findById(id);
        return Response.ok(EnderecoClienteResponseDTO.fromEntity(endereco)).build();
    }

    @Override
    @Transactional
    public EnderecoClienteResponseDTO salvar(EnderecoClienteRequestDTO dto) {
        
        String email = jwt.getName();
        Cliente cliente = clienteRepository.findByEmail(email);
        if (cliente == null) {
            throw new RuntimeException("Cliente não encontrado com e-mail: " + email);
        }
        
        EnderecoCliente endereco = new EnderecoCliente();
        endereco.setRua(dto.rua());
        endereco.setNumero(dto.numero());
        endereco.setBairro(dto.bairro());
        endereco.setCidade(dto.cidade());
        endereco.setCep(dto.cep());
        endereco.setTipoEndereco(dto.tipoEndereco());
        endereco.setEstado(estadoRepository.findById(dto.estadoId()));
        endereco.setMunicipio(municipioRepository.findById(dto.municipioId()));
        endereco.setCliente(cliente);
        repository.persist(endereco);

        return EnderecoClienteResponseDTO.fromEntity(endereco);

    }

    @Override
    @Transactional
    public void atualizar(Long id, EnderecoClienteRequestDTO dto) {
        EnderecoCliente endereco = repository.findById(id);
        endereco.setRua(dto.rua());
        endereco.setNumero(dto.numero());
        endereco.setBairro(dto.bairro());
        endereco.setCidade(dto.cidade());
        endereco.setCep(dto.cep());
        endereco.setTipoEndereco(dto.tipoEndereco());
        endereco.setEstado(estadoRepository.findById(dto.estadoId()));
        endereco.setMunicipio(municipioRepository.findById(dto.municipioId()));
    }

    @Override
    @Transactional
    public void deletar(Long id) {
        repository.deleteById(id);
    }
}
