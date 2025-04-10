package com.ecommerce.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

@Entity
@Table(name = "municipios")
public class Municipio extends DefaultEntity {

    @NotBlank(message = "Nome do município é obrigatório")
    @Size(max = 100, message = "Nome pode ter no máximo 100 caracteres")
    private String nome;

    @ManyToOne(optional = false)
    @JoinColumn(name = "estado_id")
    private Estado estado;

    public String getNome() { return nome; }
    public void setNome(String nome) { this.nome = nome; }

    public Estado getEstado() { return estado; }
    public void setEstado(Estado estado) { this.estado = estado; }
}
