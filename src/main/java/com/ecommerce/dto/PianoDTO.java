package com.ecommerce.dto;

import com.ecommerce.enumerator.TipoPiano;

import jakarta.validation.constraints.*;

public record PianoDTO(

    @NotBlank(message = "Não pode estár vazio")
    String modelo,
    String fabricante,
    
    @NotEmpty(message = "O Piano deve ter a quantidade de teclas")
    @Max(88)
    Integer numeroDeTeclas,
    boolean possuiPedais,
    String material,
    TipoPiano tipo,

    @NotNull
    @Min(value = 1, message = "Não pode ser Nulo")
    Integer idPiano) {

}
