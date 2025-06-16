package com.ecommerce.resource;

import com.ecommerce.model.Usuario;
import com.ecommerce.repository.UsuarioRepository;

import jakarta.annotation.security.RolesAllowed;
import jakarta.inject.Inject;
import jakarta.validation.Valid;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.Context;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.core.SecurityContext;

import org.mindrot.jbcrypt.BCrypt;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Path("/usuarios")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class UsuarioResource {

    private static final Logger logger = LoggerFactory.getLogger(UsuarioResource.class);

    @Inject
    UsuarioRepository usuarioRepository;

    @POST
    public Response criarUsuario(@Valid Usuario usuario) {
        logger.info("Criando novo usuário: {}", usuario.getEmail());

        String senhaHash = BCrypt.hashpw(usuario.getSenha(), BCrypt.gensalt());
        usuario.setSenha(senhaHash);

        usuarioRepository.persist(usuario);

        logger.info("Usuário {} criado com sucesso", usuario.getEmail());
        return Response.status(Response.Status.CREATED).entity(usuario).build();
    }

    @GET
    @Path("/Eu")
    @RolesAllowed({"ADMIN", "CLIENTE"})
    public Usuario getMe(@Context SecurityContext securityContext) {
        String email = securityContext.getUserPrincipal().getName();
        return usuarioRepository.findByEmail(email);
    }
}
