package com.ecommerce.dto;

import jakarta.validation.constraints.*;

import com.ecommerce.enumerator.TipoPiano;

public record PianoDTO(

    @NotBlank(message = "O modelo do piano não pode estar em branco")
    String modelo,

    String fabricante,

    @NotNull(message = "O preço do piano é obrigatório")
    Double preco,

    @NotNull(message = "A quantidade de teclas é obrigatória")
    @Min(value = 1, message = "Deve ter pelo menos 1 tecla")
    @Max(value = 88, message = "Não pode ter mais que 88 teclas")
    Integer numeroDeTeclas,

    boolean possuiPedais,

    String material,

    @NotNull(message = "O tipo do piano é obrigatório")
    TipoPiano tipo,

    @NotNull(message = "A quantidade em estoque é obrigatória")
    @Min(value = 0, message = "Não pode haver quantidade negativa em estoque")
    Integer estoque,

    @NotNull(message = "O ID da marca é obrigatório")
    Long marcaId,

    @NotNull(message = "O ID do fornecedor é obrigatório")
    Long fornecedorId
) {}
