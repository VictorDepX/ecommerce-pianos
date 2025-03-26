package com.ecommerce.dto;

import com.ecommerce.model.Piano;
import io.quarkus.runtime.annotations.RegisterForReflection;
import com.ecommerce.enumerator.TipoPiano;

@RegisterForReflection
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
            piano.id,
            piano.modelo,
            piano.fabricante,
            piano.numeroDeTeclas,
            piano.possuiPedais,
            piano.material,
            piano.tipo
        );
    }
}

