package com.ecommerce.service;

import com.ecommerce.dto.EnderecoClienteRequestDTO;
import jakarta.ws.rs.core.Response;

public interface EnderecoClienteService {
    Response listarTodos();
    Response buscarPorId(Long id);
    void salvar(EnderecoClienteRequestDTO dto);
    void atualizar(Long id, EnderecoClienteRequestDTO dto);
    void deletar(Long id);
}
