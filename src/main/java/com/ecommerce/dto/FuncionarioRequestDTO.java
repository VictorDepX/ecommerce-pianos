package com.ecommerce.dto;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Positive;

public record FuncionarioRequestDTO(
    @NotBlank 
    String nome,

    @NotBlank 
    String telefone,

    @Pattern(regexp = "\\d{11}") 
    String cpf,

    @NotBlank 
    String email,

    @NotBlank 
    String senha,

    @Positive 
    Double salario,

    @NotBlank 
    String departamento) {

}