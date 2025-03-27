package com.ecommerce.dto;

import com.ecommerce.model.Piano;
import com.ecommerce.enumerator.TipoPiano;

public record PianoResponseDTO(
    Long id,
    String modelo,
    String fabricante,
    int numeroDeTeclas,
    boolean possuiPedais,
    String material,
    TipoPiano tipo
) {
    public static PianoResponseDTO valueOf(Piano piano) {
        
        if (piano == null)
            return null;
        return new PianoResponseDTO(
            piano.getId(),
            piano.getModelo(),
            piano.getFabricante(),
            piano.getNumerodeteclas(),
            piano.getPossuipedais(),
            piano.getMaterial(),
            piano.getTipo()
        );
    }
}

