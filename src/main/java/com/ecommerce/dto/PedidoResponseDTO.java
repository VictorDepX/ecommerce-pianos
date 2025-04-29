package com.ecommerce.dto;

import com.ecommerce.enumerator.StatusPedido;
import com.ecommerce.model.Pedido;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

public record PedidoResponseDTO(
    Long id,
    LocalDateTime dataCriacao,
    StatusPedido status,
    Double total,
    Long clienteId,
    Long enderecoPedidoId,
    List<ItemPedidoResponseDTO> itens
) {
    public static PedidoResponseDTO fromEntity(Pedido pedido) {
        return new PedidoResponseDTO(
            pedido.getId(),
            pedido.getDataCriacao(),
            pedido.getStatus(),
            pedido.getTotal(),
            pedido.getCliente().getId(),
            pedido.getEnderecoPedido().getId(),
            pedido.getItens().stream().map(ItemPedidoResponseDTO::fromEntity).collect(Collectors.toList())
        );
    }
}
