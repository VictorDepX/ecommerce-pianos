package com.ecommerce.service;

import com.ecommerce.dto.MarcaRequestDTO;
import jakarta.ws.rs.core.Response;

public interface MarcaService {
    Response listarTodos();
    Response buscarPorId(Long id);
    void salvar(MarcaRequestDTO dto);
    void atualizar(Long id, MarcaRequestDTO dto);
    void deletar(Long id);
}
