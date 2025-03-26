package com.ecommerce.model;

import io.quarkus.hibernate.orm.panache.PanacheEntity;
import jakarta.persistence.*;

@Entity
@Table(name = "pianos")
public class Piano extends PanacheEntity {
    public String modelo;
    public String fabricante;
    public int numeroDeTeclas;
    public boolean possuiPedais;
    public String material;

    @Enumerated(EnumType.STRING)
    public com.ecommerce.enumerator.TipoPiano tipo;
}