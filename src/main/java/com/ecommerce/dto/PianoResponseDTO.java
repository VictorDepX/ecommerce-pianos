package com.ecommerce.dto;

import com.ecommerce.model.Piano;
import com.ecommerce.enumerator.TipoPiano;

public record PianoResponseDTO(
    Long id,
    String modelo,
    Double preco,
    String fabricante,
    int numeroDeTeclas,
    boolean possuiPedais,
    String material,
    TipoPiano tipo,
    Integer estoque,
    String marca,
    String fornecedor
) {
    public static PianoResponseDTO fromEntity(Piano piano) {
        
        if (piano == null)
            return null;
        return new PianoResponseDTO(
            piano.getId(),
            piano.getModelo(),
            piano.getPreco(),
            piano.getFabricante(),
            piano.getNumerodeteclas(),
            piano.getPossuipedais(),
            piano.getMaterial(),
            piano.getTipo(),
            piano.getEstoque(),
            piano.getMarca().getNome(),
            piano.getFornecedor().getNome()
        );
    }
}

