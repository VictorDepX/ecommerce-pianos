package com.ecommerce.service;

import com.ecommerce.dto.FuncionarioResponseDTO;
import com.ecommerce.dto.FuncionarioRequestDTO;
import com.ecommerce.model.Funcionario;
import com.ecommerce.model.Usuario;
import com.ecommerce.repository.FuncionarioRepository;
import com.ecommerce.util.BCryptUtil;
import com.ecommerce.enumerator.Perfil;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;

import java.util.List;

@ApplicationScoped
public class FuncionarioServiceImpl implements FuncionarioService {

    @Inject FuncionarioRepository repository;

    @Override
    public List<FuncionarioResponseDTO> listarTodos() {
        return repository.findAll().stream().map(FuncionarioResponseDTO::fromEntity).toList();
    }

    @Override
    public FuncionarioResponseDTO buscarPorId(Long id) {
        return FuncionarioResponseDTO.fromEntity(repository.findById(id));
    }

    @Override
    @Transactional
    public FuncionarioResponseDTO salvar(FuncionarioRequestDTO dto) {
        Funcionario f = new Funcionario();
        Usuario u = new Usuario();
        u.setEmail(dto.email());
        u.setSenha(BCryptUtil.hashPassword(dto.senha()));
        u.setPerfil(Perfil.FUNCIONARIO);
        f.setNome(dto.nome());
        f.setTelefone(dto.telefone());
        f.setCpf(dto.cpf());
        f.setSalario(dto.salario());
        f.setDepartamento(dto.departamento());
        f.setUsuario(u);
        repository.persist(f);

        return FuncionarioResponseDTO.fromEntity(f);
    }

    @Override
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

    @Override
    @Transactional
    public void deletar(Long id) {
        repository.deleteById(id);
    }
}
