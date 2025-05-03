package com.ecommerce.model;

import java.util.List;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;

@Entity
@Table(name = "fornecedores")
public class Fornecedor extends DefaultEntity {

    @NotBlank(message = "Nome não pode estar em branco")
    @Size(max = 100, message = "Nome pode ter no máximo 100 caracteres")
    private String nome;

    @NotBlank(message = "CNPJ não pode estar em branco")
    @Pattern(regexp = "\\d{14}", message = "CNPJ deve conter exatamente 14 dígitos numéricos")
    private String cnpj;

    @NotBlank(message = "Telefone não pode estar em branco")
    @Size(max = 20, message = "Telefone pode ter no máximo 20 caracteres")
    private String telefone;

    @ManyToMany
    @JoinTable(
        name = "fornecedor_marca",
        joinColumns = @JoinColumn(name = "fornecedor_id"),
        inverseJoinColumns = @JoinColumn(name = "marca_id")
    )
    @NotEmpty
    private List<Marca> marcas;

    public String getNome() {
        return nome;
    }
    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getCnpj() {
        return cnpj;
    }

    public void setCnpj(String cnpj) {
        this.cnpj = cnpj;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public List<Marca> getMarcas() {
        return marcas;
    }

    public void setMarcas(List<Marca> marcas) {
        this.marcas = marcas;
    }
}
