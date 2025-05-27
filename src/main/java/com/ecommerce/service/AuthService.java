package com.ecommerce.service;

import com.ecommerce.dto.LoginDTO;
import com.ecommerce.model.Usuario;
import com.ecommerce.repository.UsuarioRepository;
import com.ecommerce.security.JwtUtil;
import com.ecommerce.util.BCryptUtil;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.ws.rs.core.Response;

@ApplicationScoped
public class AuthService {

    @Inject
    UsuarioRepository usuarioRepository;

    @Inject
    JwtUtil jwtUtil;

    public Response login(LoginDTO dto) {
        Usuario usuario = usuarioRepository.findByEmail(dto.email());
        if (usuario == null || !BCryptUtil.checkPassword(dto.senha(), usuario.getSenha())) {
            return Response.status(Response.Status.UNAUTHORIZED).build();
        }

        String token = jwtUtil.generateToken(usuario);
        return Response.ok().header("Authorization", "Bearer " + token).build();
    }
}
