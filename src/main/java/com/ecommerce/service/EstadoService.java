package com.ecommerce.service;

import com.ecommerce.dto.EstadoRequestDTO;
import jakarta.ws.rs.core.Response;

public interface EstadoService {
    Response listarTodos();
    Response buscarPorId(Long id);
    Response buscarPorSigla(String sigla);
    void salvar(EstadoRequestDTO dto);
    void atualizar(Long id, EstadoRequestDTO dto);
    void deletar(Long id);
}
