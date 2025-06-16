package com.ecommerce.repository;

import java.util.List;

import com.ecommerce.model.Pedido;
import io.quarkus.hibernate.orm.panache.PanacheRepository;
import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class PedidoRepository implements PanacheRepository<Pedido> {

    public List<Pedido> buscarPorNome(String username) {
        return find("cliente.nome = ?1", username).list();
    }

}
