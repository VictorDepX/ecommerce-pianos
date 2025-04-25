package com.ecommerce.model;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;

@Entity
@DiscriminatorValue("EnderecoCliente")
public class EnderecoCliente extends Endereco {
    // Herda atributos e mapeamentos da classe Endereco
}
