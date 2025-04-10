package com.ecommerce.repository;

import com.ecommerce.model.Piano;
import io.quarkus.hibernate.orm.panache.PanacheRepository;
import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class PianoRepository implements PanacheRepository<Piano> {
    
    public Piano buscarPorFabricante(String fabricante) {
        return find("fabricante", fabricante).firstResult();
    }
}
