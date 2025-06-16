
package com.ecommerce.service;

import com.ecommerce.dto.ClienteRequestDTO;
import com.ecommerce.dto.ClienteResponseDTO;
import com.ecommerce.model.Cliente;
import com.ecommerce.model.Usuario;
import com.ecommerce.repository.ClienteRepository;
import com.ecommerce.util.BCryptUtil;
import com.ecommerce.enumerator.Perfil;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import jakarta.ws.rs.core.Response;

import java.util.List;

@ApplicationScoped
public class ClienteServiceImpl implements ClienteService {

    @Inject ClienteRepository repository;

    @Override
    public List<ClienteResponseDTO> listarTodos() {
        return repository.listAll().stream()
            .map(ClienteResponseDTO::fromEntity)
            .toList();
    }

    public Response buscarPorId(Long id) {
        Cliente c = repository.findById(id);
        return Response.ok(ClienteResponseDTO.fromEntity(c)).build();
    }

    @Override
    @Transactional
    public ClienteResponseDTO salvar(ClienteRequestDTO dto) {
        Cliente c = new Cliente();
        Usuario u = new Usuario();
        u.setEmail(dto.email());
        u.setSenha(BCryptUtil.hashPassword(dto.senha()));
        u.setPerfil(Perfil.CLIENTE);
        c.setNome(dto.nome());
        c.setTelefone(dto.telefone());
        c.setCpf(dto.cpf());
        c.setUsuario(u);
        repository.persist(c);
        
        return ClienteResponseDTO.fromEntity(c);
    }

    @Override
    @Transactional
    public void atualizar(Long id, ClienteRequestDTO dto) {
        Cliente c = repository.findById(id);
        c.setNome(dto.nome());
        c.setTelefone(dto.telefone());
        c.setCpf(dto.cpf());
        c.getUsuario().setEmail(dto.email());
        c.getUsuario().setSenha(dto.senha());
    }

    @Override
    @Transactional
    public void deletar(Long id) {
        repository.deleteById(id);
    }
}
