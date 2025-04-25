package com.ecommerce.dto;
import com.ecommerce.model.Cliente;

public record ClienteResponseDTO(
    Long id,
    String nome,
    String telefone,
    String cpf,
    String email
) {
    public static ClienteResponseDTO fromEntity(Cliente cliente) {
        return new ClienteResponseDTO(
            cliente.getId(), 
            cliente.getNome(), 
            cliente.getTelefone(), 
            cliente.getCpf(), 
            cliente.getUsuario().getEmail()
        );
    }
}