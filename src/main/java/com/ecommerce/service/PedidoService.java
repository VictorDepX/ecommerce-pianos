package com.ecommerce.service;

import com.ecommerce.dto.PedidoRequestDTO;
import com.ecommerce.dto.PedidoResponseDTO;

import jakarta.ws.rs.core.Response;

public interface PedidoService {
    Response listarTodos();
    Response buscarPorId(Long id);
    PedidoResponseDTO salvar(PedidoRequestDTO dto);
    void deletar(Long id);
}
