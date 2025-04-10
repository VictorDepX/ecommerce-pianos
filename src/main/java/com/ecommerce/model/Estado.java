package com.ecommerce.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

@Entity
@Table(name = "estados")
public class Estado extends DefaultEntity {

    @NotBlank(message = "O nome do estado é obrigatório")
    @Size(max = 100, message = "Nome pode ter no máximo 100 caracteres")
    private String nome;

    @NotBlank(message = "A sigla é obrigatória")
    @Size(min = 2, max = 2, message = "A sigla deve ter exatamente 2 caracteres")
    private String sigla;

    // Getters e Setters
    public String getNome() { return nome; }
    public void setNome(String nome) { this.nome = nome; }

    public String getSigla() { return sigla; }
    public void setSigla(String sigla) { this.sigla = sigla; }
}
