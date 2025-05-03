package com.ecommerce.service;

import java.util.List;

import com.ecommerce.dto.ClienteRequestDTO;
import com.ecommerce.dto.ClienteResponseDTO;

import jakarta.ws.rs.core.Response;

public interface ClienteService {
    List<ClienteResponseDTO> listarTodos();
    Response buscarPorId(Long id);
    ClienteResponseDTO salvar(ClienteRequestDTO dto);
    void atualizar(Long id, ClienteRequestDTO dto);
    void deletar(Long id);
}