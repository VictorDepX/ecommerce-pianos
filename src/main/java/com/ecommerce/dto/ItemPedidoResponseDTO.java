package com.ecommerce.dto;

import com.ecommerce.model.ItemPedido;

public record ItemPedidoResponseDTO(
    Long id,
    String modeloPiano,
    int quantidade,
    Double subtotal
) {
    public static ItemPedidoResponseDTO fromEntity(ItemPedido item) {
        return new ItemPedidoResponseDTO(
            item.getId(),
            item.getPiano().getModelo(),
            item.getQuantidade(),
            item.getSubtotal()
        );
    }
}
