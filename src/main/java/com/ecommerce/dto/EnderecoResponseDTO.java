package com.ecommerce.dto;

import com.ecommerce.model.Endereco;

public record EnderecoResponseDTO(
    Long id,
    String rua,
    String numero,
    String bairro,
    String cidade,
    String cep,
    String tipoEndereco,
    String municipio,
    String estado
) {
    public static EnderecoResponseDTO fromEntity(Endereco endereco) {
        return new EnderecoResponseDTO(
            endereco.getId(), 
            endereco.getRua(), 
            endereco.getNumero(), 
            endereco.getBairro(),
            endereco.getCidade(), 
            endereco.getCep(), 
            endereco.getTipoEndereco().name(),
            endereco.getMunicipio().getNome(), 
            endereco.getEstado().getNome()
        );
    }
}
