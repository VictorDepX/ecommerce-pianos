package com.ecommerce.repository;

import java.util.List;

import com.ecommerce.model.Usuario;
import io.quarkus.hibernate.orm.panache.PanacheRepository;
import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class UsuarioRepository implements PanacheRepository<Usuario> {

    public Usuario findByEmail(String email) {
        return find("email", email).firstResult();
    }

    public List<Usuario> findByUsername(String username) {
        return find("cliente.nome = ?1", username).list();
    }
}