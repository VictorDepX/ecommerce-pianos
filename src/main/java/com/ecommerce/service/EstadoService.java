package com.ecommerce.service;

import java.util.List;

import com.ecommerce.dto.EstadoRequestDTO;
import com.ecommerce.dto.EstadoResponseDTO;


public interface EstadoService {
    List<EstadoResponseDTO> listarTodos();
    EstadoResponseDTO buscarPorId(long id);
    EstadoResponseDTO buscarPorSigla(String sigla);
    EstadoResponseDTO salvar(EstadoRequestDTO dto);
    void atualizar(Long id, EstadoRequestDTO dto);
    void deletar(long id);
}
