package com.ecommerce.repository;

import com.ecommerce.model.Estado;
import io.quarkus.hibernate.orm.panache.PanacheRepository;
import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class EstadoRepository implements PanacheRepository<Estado> {
    public Estado buscarPorSigla(String sigla) {
        return find("sigla", sigla).firstResult();
    }
}
