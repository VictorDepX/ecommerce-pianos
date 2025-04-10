package com.ecommerce.dto;

import com.ecommerce.model.Municipio;

public record MunicipioResponseDTO(
    Long id, 
    String nome, 
    String estado) {
    public static MunicipioResponseDTO fromEntity(Municipio municipio) {
        return new MunicipioResponseDTO(
            municipio.getId(), 
            municipio.getNome(), 
            municipio.getEstado().getNome()
            );
    }
}
