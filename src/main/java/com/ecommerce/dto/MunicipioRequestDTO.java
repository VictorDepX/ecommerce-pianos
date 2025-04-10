package com.ecommerce.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import jakarta.validation.constraints.NotNull;

public record MunicipioRequestDTO(
    @NotBlank(message = "Nome é obrigatório")
    @Size(max = 100)
    String nome,

    @NotNull(message = "ID do estado é obrigatório")
    Long estadoId) {
        
}
