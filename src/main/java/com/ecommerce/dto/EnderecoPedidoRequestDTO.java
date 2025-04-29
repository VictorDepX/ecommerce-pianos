package com.ecommerce.dto;
import com.ecommerce.enumerator.TipoEndereco;
import jakarta.validation.constraints.NotBlank;

public record EnderecoPedidoRequestDTO(
    @NotBlank String rua,
    @NotBlank String numero,
    @NotBlank String bairro,
    @NotBlank String cidade,
    @NotBlank String cep,
    TipoEndereco tipoEndereco,
    Long municipioId,
    Long estadoId
) {}
