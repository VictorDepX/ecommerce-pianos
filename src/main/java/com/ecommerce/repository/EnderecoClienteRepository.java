package com.ecommerce.repository;
import java.util.List;

import com.ecommerce.model.EnderecoCliente;

import io.quarkus.hibernate.orm.panache.PanacheRepository;
import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class EnderecoClienteRepository implements PanacheRepository<EnderecoCliente> {

    public List<EnderecoCliente> buscarPorNome(String username) {
        return find("cliente.nome = ?1", username).list();
    }

}
