package com.ecommerce.dto;

import com.ecommerce.model.Marca;

public record MarcaResponseDTO(
    Long id,
    String nome,
    String cnpj
) {
    public static MarcaResponseDTO fromEntity(Marca marca) {
        return new MarcaResponseDTO(
            marca.getId(), 
            marca.getNome(), 
            marca.getCnpj());
    }
}
