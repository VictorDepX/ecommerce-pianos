package com.ecommerce.service;

import java.util.List;

import com.ecommerce.dto.MunicipioRequestDTO;
import com.ecommerce.dto.MunicipioResponseDTO;

import jakarta.ws.rs.core.Response;

public interface MunicipioService {
    List<MunicipioResponseDTO> listarTodos();
    MunicipioResponseDTO buscarPorId(Long id);
    MunicipioResponseDTO salvar(MunicipioRequestDTO dto);
    void atualizar(Long id, MunicipioRequestDTO dto);
    void deletar(Long id);
}
