package com.ecommerce.resource;

import com.ecommerce.model.Usuario;
import com.ecommerce.repository.UsuarioRepository;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import org.mindrot.jbcrypt.BCrypt;

@Path("/usuarios")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class UsuarioResource {

    @Inject
    UsuarioRepository usuarioRepository;

    @POST
    @Transactional
    public Response criarUsuario(Usuario usuario) {
        String hash = BCrypt.hashpw(usuario.getSenha(), BCrypt.gensalt());
        usuario.setSenha(hash);
        usuarioRepository.persist(usuario);
        return Response.status(Response.Status.CREATED).entity(usuario).build();
    }
}