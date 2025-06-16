package com.ecommerce.service;

import java.util.List;

import com.ecommerce.dto.FornecedorRequestDTO;
import com.ecommerce.dto.FornecedorResponseDTO;

import jakarta.ws.rs.core.Response;

public interface FornecedorService {
    List<FornecedorResponseDTO> listarTodos();
    Response buscarPorId(Long id);
    FornecedorResponseDTO buscarPorNome(String nome);
    FornecedorResponseDTO buscarPorCnpj(String cnpj);
    FornecedorResponseDTO buscarPorTelefone(String telefone);
    FornecedorResponseDTO salvar(FornecedorRequestDTO dto);
    void atualizar(Long id, FornecedorRequestDTO dto);
    void deletar(Long id);
}
