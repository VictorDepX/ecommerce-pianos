package com.ecommerce.model;
import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import jakarta.persistence.OneToOne;

@Entity
@DiscriminatorValue("EnderecoPedido")
public class EnderecoPedido extends Endereco {

    @OneToOne(mappedBy = "enderecoPedido")
    private Pedido pedido;
    
}
