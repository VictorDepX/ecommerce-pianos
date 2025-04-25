package com.ecommerce.dto;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;

public record ClienteRequestDTO(
    @NotBlank 
    String nome,
    @NotBlank 
    String telefone,

    @Pattern(regexp = "\\d{11}") 
    String cpf,

    @NotBlank 
    String email,

    @NotBlank 
    String senha) {

}