package com.ecommerce.service;

import java.util.List;

import com.ecommerce.dto.FuncionarioRequestDTO;
import com.ecommerce.dto.FuncionarioResponseDTO;

public interface FuncionarioService {
    List<FuncionarioResponseDTO> listarTodos();
    FuncionarioResponseDTO buscarPorId(Long id);
    FuncionarioResponseDTO salvar(FuncionarioRequestDTO dto);
    void atualizar(Long id, FuncionarioRequestDTO dto);
    void deletar(Long id);
}