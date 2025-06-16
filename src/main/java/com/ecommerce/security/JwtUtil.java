package com.ecommerce.security;

import com.ecommerce.model.Usuario;
import io.smallrye.jwt.build.Jwt;

import jakarta.enterprise.context.ApplicationScoped;
import java.time.Duration;
import java.util.Set;

@ApplicationScoped
public class JwtUtil {

    public String generateToken(Usuario usuario) {
        return Jwt.issuer("ecommerce-api")
                .upn(usuario.getEmail())
                .groups(Set.of(usuario.getPerfil().name()))
                .expiresIn(Duration.ofHours(5))
                .sign();
    }
}
