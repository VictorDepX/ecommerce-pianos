package com.ecommerce.dto;
import com.ecommerce.model.EnderecoCliente;

public record EnderecoClienteResponseDTO(
    Long id,
    String rua,
    String numero,
    String bairro,
    String cidade,
    String cep,
    String tipoEndereco,
    String estado,
    String municipio,
    String cliente
) {
    public static EnderecoClienteResponseDTO fromEntity(EnderecoCliente e) {
        return new EnderecoClienteResponseDTO(
            e.getId(), e.getRua(), e.getNumero(), e.getBairro(),
            e.getCidade(), e.getCep(), e.getTipoEndereco().name(),
            e.getEstado().getNome(), e.getMunicipio().getNome(), e.getCliente().getNome()
        );
    }
}
