package com.ecommerce.model;
import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;

@Entity
@DiscriminatorValue("EnderecoPedido")
public class EnderecoPedido extends Endereco {}
