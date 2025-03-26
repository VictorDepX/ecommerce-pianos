package com.ecommerce.dto;

import com.ecommerce.model.Piano;
import com.ecommerce.enumerator.TipoPiano;

public record PianoRequestDTO(
    String modelo,
    String fabricante,
    int numeroDeTeclas,
    boolean possuiPedais,
    String material,
    TipoPiano tipo
) {
    public Piano toEntity() {
        Piano piano = new Piano();
        piano.modelo = this.modelo;
        piano.fabricante = this.fabricante;
        piano.numeroDeTeclas = this.numeroDeTeclas;
        piano.possuiPedais = this.possuiPedais;
        piano.material = this.material;
        piano.tipo = this.tipo;
        return piano;
    }
}
