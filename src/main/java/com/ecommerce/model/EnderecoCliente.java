package com.ecommerce.model;
import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

@Entity
@DiscriminatorValue("EnderecoCliente")
public class EnderecoCliente extends Endereco {
    
    @ManyToOne
    @JoinColumn(name = "cliente_id")
    private Cliente cliente;
    
}
