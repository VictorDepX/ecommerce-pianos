package com.ecommerce.service;

import com.ecommerce.dto.ItemPedidoRequestDTO;
import jakarta.ws.rs.core.Response;

public interface ItemPedidoService {
    Response listarTodos();
    Response buscarPorId(Long id);
    void salvar(ItemPedidoRequestDTO dto);
    void atualizar(Long id, ItemPedidoRequestDTO dto);
    void deletar(Long id);
}
