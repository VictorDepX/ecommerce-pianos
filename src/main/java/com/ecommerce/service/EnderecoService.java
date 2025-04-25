package com.ecommerce.service;

import com.ecommerce.dto.EnderecoRequestDTO;
import jakarta.ws.rs.core.Response;

public interface EnderecoService {
    Response listarTodos();
    Response buscarPorId(Long id);
    void salvar(EnderecoRequestDTO dto);
    void atualizar(Long id, EnderecoRequestDTO dto);
    void deletar(Long id);
}
