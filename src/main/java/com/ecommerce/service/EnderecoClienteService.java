package com.ecommerce.service;

import java.util.List;

import com.ecommerce.dto.EnderecoClienteRequestDTO;
import com.ecommerce.dto.EnderecoClienteResponseDTO;

import jakarta.ws.rs.core.Response;

public interface EnderecoClienteService {
    List<EnderecoClienteResponseDTO> buscarPorNome(String nome);
    Response buscarPorId(Long id);
    EnderecoClienteResponseDTO salvar(EnderecoClienteRequestDTO dto);
    void atualizar(Long id, EnderecoClienteRequestDTO dto);
    void deletar(Long id);
}
