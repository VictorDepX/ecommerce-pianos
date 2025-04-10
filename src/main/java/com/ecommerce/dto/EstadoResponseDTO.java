package com.ecommerce.dto;

import com.ecommerce.model.Estado;

public record EstadoResponseDTO(
    Long id, 
    String nome, 
    String sigla) {
    public static EstadoResponseDTO fromEntity(Estado estado) {
        return new EstadoResponseDTO(
            estado.getId(), 
            estado.getNome(), 
            estado.getSigla());
    }
}
