package com.ecommerce.service;

import com.ecommerce.dto.MunicipioRequestDTO;
import com.ecommerce.dto.MunicipioResponseDTO;

import jakarta.ws.rs.core.Response;

public interface MunicipioService {
    Response listarTodos();
    MunicipioResponseDTO buscarPorId(Long id);
    MunicipioResponseDTO salvar(MunicipioRequestDTO dto);
    void atualizar(Long id, MunicipioRequestDTO dto);
    void deletar(Long id);
}
