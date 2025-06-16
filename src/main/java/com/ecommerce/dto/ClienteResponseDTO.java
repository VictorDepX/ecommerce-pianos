package com.ecommerce.dto;

import java.util.List;
import com.ecommerce.model.Cliente;

public record ClienteResponseDTO(
    Long id,
    String nome,
    String telefone,
    String cpf,
    String email,
    List<EnderecoClienteResponseDTO> enderecos
) {
    public static ClienteResponseDTO fromEntity(Cliente cliente) {
        return new ClienteResponseDTO(
            cliente.getId(), 
            cliente.getNome(), 
            cliente.getTelefone(), 
            cliente.getCpf(),
            cliente.getUsuario().getEmail(),
            cliente.getEnderecos().stream()
                        .map(EnderecoClienteResponseDTO::fromEntity)
                        .toList()
        );
    }
}