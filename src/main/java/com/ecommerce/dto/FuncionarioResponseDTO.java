package com.ecommerce.dto;
import com.ecommerce.model.Funcionario;

public record FuncionarioResponseDTO(
    Long id,
    String nome,
    String telefone,
    String cpf,
    String email,
    Double salario,
    String departamento
) {
    public static FuncionarioResponseDTO fromEntity(Funcionario funcionario) {
        return new FuncionarioResponseDTO(
            funcionario.getId(), 
            funcionario.getNome(), 
            funcionario.getTelefone(), 
            funcionario.getCpf(),
            funcionario.getUsuario().getEmail(), 
            funcionario.getSalario(), 
            funcionario.getDepartamento()
        );
    }
}