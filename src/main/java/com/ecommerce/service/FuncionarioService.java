package com.ecommerce.service;
import com.ecommerce.dto.FuncionarioRequestDTO;
import jakarta.ws.rs.core.Response;

public interface FuncionarioService {
    Response listarTodos();
    Response buscarPorId(Long id);
    void salvar(FuncionarioRequestDTO dto);
    void atualizar(Long id, FuncionarioRequestDTO dto);
    void deletar(Long id);
}