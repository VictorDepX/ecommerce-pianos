package com.ecommerce.service;

import java.util.List;

import com.ecommerce.dto.EnderecoRequestDTO;
import com.ecommerce.dto.EnderecoResponseDTO;

import jakarta.ws.rs.core.Response;

public interface EnderecoService {
    List<EnderecoResponseDTO> listarTodos();
    Response buscarPorId(Long id);
    void salvar(EnderecoRequestDTO dto);
    void atualizar(Long id, EnderecoRequestDTO dto);
    void deletar(Long id);
}
