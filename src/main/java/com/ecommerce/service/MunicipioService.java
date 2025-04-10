package com.ecommerce.service;

import com.ecommerce.dto.MunicipioRequestDTO;
import jakarta.ws.rs.core.Response;

public interface MunicipioService {
    Response listarTodos();
    Response buscarPorId(Long id);
    void salvar(MunicipioRequestDTO dto);
    void atualizar(Long id, MunicipioRequestDTO dto);
    void deletar(Long id);
}
