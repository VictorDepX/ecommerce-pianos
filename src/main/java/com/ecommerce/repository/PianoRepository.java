package com.ecommerce.repository;

import com.ecommerce.model.Piano;
import io.quarkus.hibernate.orm.panache.PanacheRepository;
import jakarta.enterprise.context.ApplicationScoped;
import java.util.List;

@ApplicationScoped
public class PianoRepository implements PanacheRepository<Piano> {
    
    public List<Piano> buscarPorFabricante(String fabricante) {
        return find("fabricante LIKE ?1", "%" + fabricante + "%").list();
    }
}
