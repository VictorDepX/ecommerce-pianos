package com.ecommerce.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record EstadoRequestDTO(
    @NotBlank(message = "Nome é obrigatório")
    @Size(max = 100)
    String nome,

    @NotBlank(message = "Sigla é obrigatória")
    @Size(min = 2, max = 2)
    String sigla) {

}
