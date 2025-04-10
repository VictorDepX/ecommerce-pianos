package com.ecommerce.dto;

import com.ecommerce.model.Fornecedor;

public record FornecedorResponseDTO(
    Long id, 
    String nome, 
    String cnpj, 
    String telefone
) { 
    public static FornecedorResponseDTO fromEntity(Fornecedor fornecedor) {
        return new FornecedorResponseDTO(
            fornecedor.getId(), 
            fornecedor.getNome(), 
            fornecedor.getCnpj(), 
            fornecedor.getTelefone()
            );
    }
}
