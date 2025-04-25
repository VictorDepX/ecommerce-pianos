package com.ecommerce.service;
import com.ecommerce.dto.ClienteRequestDTO;
import jakarta.ws.rs.core.Response;

public interface ClienteService {
    Response listarTodos();
    Response buscarPorId(Long id);
    void salvar(ClienteRequestDTO dto);
    void atualizar(Long id, ClienteRequestDTO dto);
    void deletar(Long id);
}