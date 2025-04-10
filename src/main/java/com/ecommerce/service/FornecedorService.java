package com.ecommerce.service;

import com.ecommerce.dto.FornecedorRequestDTO;
import jakarta.ws.rs.core.Response;

public interface FornecedorService {
    Response listarTodos();
    Response buscarPorId(Long id);
    Response buscarPorNome(String nome);
    Response buscarPorCnpj(String cnpj);
    Response buscarPorTelefone(String telefone);
    void salvar(FornecedorRequestDTO dto);
    void atualizar(Long id, FornecedorRequestDTO dto);
    void deletar(Long id);
}
