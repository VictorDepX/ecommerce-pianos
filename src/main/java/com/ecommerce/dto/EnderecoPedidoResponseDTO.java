package com.ecommerce.dto;
import com.ecommerce.model.EnderecoPedido;

public record EnderecoPedidoResponseDTO(
    Long id,
    String rua,
    String numero,
    String bairro,
    String cidade,
    String cep,
    String tipoEndereco,
    String estado,
    String municipio
) {
    public static EnderecoPedidoResponseDTO fromEntity(EnderecoPedido e) {
        return new EnderecoPedidoResponseDTO(
            e.getId(), e.getRua(), e.getNumero(), e.getBairro(),
            e.getCidade(), e.getCep(), e.getTipoEndereco().name(),
            e.getEstado().getNome(), e.getMunicipio().getNome()
        );
    }
}
