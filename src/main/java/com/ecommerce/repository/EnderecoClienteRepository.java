package com.ecommerce.repository;

import com.ecommerce.model.EnderecoCliente;
import io.quarkus.hibernate.orm.panache.PanacheRepository;
import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class EnderecoClienteRepository implements PanacheRepository<EnderecoCliente> {}
