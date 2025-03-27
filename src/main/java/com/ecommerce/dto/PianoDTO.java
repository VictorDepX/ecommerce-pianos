package com.ecommerce.dto;

import com.ecommerce.enumerator.TipoPiano;

public record PianoDTO(
    String modelo,
    String fabricante,
    Integer numeroDeTeclas,
    boolean possuiPedais,
    String material,
    TipoPiano tipo,
    Integer idPiano) {

}
