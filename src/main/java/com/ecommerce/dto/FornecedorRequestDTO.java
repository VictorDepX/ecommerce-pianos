package com.ecommerce.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

public record FornecedorRequestDTO(

    @NotBlank(message = "Nome não pode estar em branco")
    @Size(max = 100, message = "Nome pode ter no máximo 100 caracteres")
    String nome,

    @NotBlank(message = "CNPJ não pode estar em branco")
    @Pattern(regexp = "\\d{14}", message = "CNPJ deve conter exatamente 14 dígitos numéricos")
    String cnpj,

    @NotBlank(message = "Telefone não pode estar em branco")
    @Size(max = 20, message = "Telefone pode ter no máximo 20 caracteres")
    String telefone) {

}
