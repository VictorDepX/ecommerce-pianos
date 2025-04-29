package com.ecommerce.dto;

import jakarta.validation.constraints.NotNull;

public record ItemPedidoRequestDTO(
    @NotNull Long pianoId,
    @NotNull Integer quantidade
) {}
