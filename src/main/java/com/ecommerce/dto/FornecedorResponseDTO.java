package com.ecommerce.dto;

import com.ecommerce.model.Fornecedor;

public record FornecedorResponseDTO(
    Long id, 
    String nome, 
    String cnpj, 
    String telefone
) { 
    public static FornecedorResponseDTO fromEntity(Fornecedor f) {
        return new FornecedorResponseDTO(
            f.getId(), 
            f.getNome(), 
            f.getCnpj(), 
            f.getTelefone());
    }
}
