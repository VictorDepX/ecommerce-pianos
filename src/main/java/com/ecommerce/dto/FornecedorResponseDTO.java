package com.ecommerce.dto;

import java.util.List;

import com.ecommerce.model.Fornecedor;
import com.ecommerce.model.Marca;

public record FornecedorResponseDTO(
    Long id, 
    String nome, 
    String cnpj, 
    String telefone,
    List<String> marcas
) { 
    public static FornecedorResponseDTO fromEntity(Fornecedor fornecedor) {
        return new FornecedorResponseDTO(
            fornecedor.getId(), 
            fornecedor.getNome(), 
            fornecedor.getCnpj(), 
            fornecedor.getTelefone(),
            fornecedor.getMarcas().stream()
            .map(Marca::getNome).toList()
            );
    }
}
