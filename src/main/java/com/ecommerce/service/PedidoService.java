package com.ecommerce.service;

import com.ecommerce.dto.PedidoRequestDTO;
import jakarta.ws.rs.core.Response;

public interface PedidoService {
    Response listarTodos();
    Response buscarPorId(Long id);
    void salvar(PedidoRequestDTO dto);
    void deletar(Long id);
}
