package com.ecommerce.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;

public record MarcaRequestDTO(

    @NotBlank 
    String nome,
    
    @Pattern(regexp = "\\d{14}", message = "CNPJ deve ter 14 dígitos") 
    String cnpj

    ) {

}
