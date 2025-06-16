package com.ecommerce.service;

import java.util.List;

import com.ecommerce.dto.MarcaRequestDTO;
import com.ecommerce.dto.MarcaResponseDTO;

import jakarta.ws.rs.core.Response;

public interface MarcaService {
    List<MarcaResponseDTO> listarTodos();
    Response buscarPorId(Long id);
    MarcaResponseDTO salvar(MarcaRequestDTO dto);
    void atualizar(Long id, MarcaRequestDTO dto);
    void deletar(Long id);
}
