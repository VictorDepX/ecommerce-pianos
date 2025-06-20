package com.ecommerce.dto;

import com.ecommerce.enumerator.StatusPedido;
import jakarta.validation.constraints.NotNull;
import java.util.List;

public record PedidoRequestDTO(

    @NotNull Long 
    enderecoClienteId,

    @NotNull 
    List<ItemPedidoRequestDTO> itens,

    StatusPedido status
) {}
