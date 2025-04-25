
package com.ecommerce.service;

import com.ecommerce.dto.FuncionarioRequestDTO;
import com.ecommerce.dto.FuncionarioResponseDTO;
import com.ecommerce.model.Funcionario;
import com.ecommerce.model.Usuario;
import com.ecommerce.repository.FuncionarioRepository;
import com.ecommerce.enumerator.Perfil;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import jakarta.ws.rs.core.Response;

import java.util.List;
import java.util.stream.Collectors;

@ApplicationScoped
public class FuncionarioServiceImpl implements FuncionarioService {

    @Inject FuncionarioRepository repository;

    public Response listarTodos() {
        List<FuncionarioResponseDTO> list = repository.listAll()
            .stream().map(FuncionarioResponseDTO::fromEntity).collect(Collectors.toList());
        return Response.ok(list).build();
    }

    public Response buscarPorId(Long id) {
        Funcionario f = repository.findById(id);
        return Response.ok(FuncionarioResponseDTO.fromEntity(f)).build();
    }

    @Transactional
    public void salvar(FuncionarioRequestDTO dto) {
        Funcionario f = new Funcionario();
        Usuario u = new Usuario();
        u.setEmail(dto.email());
        u.setSenha(dto.senha());
        u.setPerfil(Perfil.FUNCIONARIO);
        f.setNome(dto.nome());
        f.setTelefone(dto.telefone());
        f.setCpf(dto.cpf());
        f.setSalario(dto.salario());
        f.setDepartamento(dto.departamento());
        f.setUsuario(u);
        repository.persist(f);
    }

    @Transactional
    public void atualizar(Long id, FuncionarioRequestDTO dto) {
        Funcionario f = repository.findById(id);
        f.setNome(dto.nome());
        f.setTelefone(dto.telefone());
        f.setCpf(dto.cpf());
        f.setSalario(dto.salario());
        f.setDepartamento(dto.departamento());
        f.getUsuario().setEmail(dto.email());
        f.getUsuario().setSenha(dto.senha());
    }

    @Transactional
    public void deletar(Long id) {
        repository.deleteById(id);
    }
}
