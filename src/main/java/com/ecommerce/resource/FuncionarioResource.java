package com.ecommerce.resource;

import com.ecommerce.dto.FuncionarioRequestDTO;
import com.ecommerce.service.FuncionarioService;
import jakarta.annotation.security.RolesAllowed;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Path("/funcionarios")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class FuncionarioResource {
    
    private static final Logger logger = LoggerFactory.getLogger(FuncionarioResource.class);

    @Inject
    FuncionarioService service;

    @GET
    @RolesAllowed("FUNCIONARIO")
    public Response listarTodos() {
        logger.info("Listando todos os funcionários");
        return Response.ok().entity(service.listarTodos()).build();
    }

    @GET
    @Path("/{id}")
    @RolesAllowed("FUNCIONARIO")
    public Response buscarPorId(@PathParam("id") Long id) {
        logger.info("Buscando funcionário com ID: {}", id);
        return Response.ok().entity(service.buscarPorId(id)).build();
    }

    @POST
    @Transactional
    public Response criarFuncionario(@Valid FuncionarioRequestDTO dto) {
        logger.info("Criando funcionário: {}", dto);
        return Response.status(Response.Status.CREATED).entity(service.salvar(dto)).build();
    }

    @PUT
    @Path("/{id}")
    @Transactional
    @RolesAllowed("FUNCIONARIO")
    public Response atualizarFuncionario(@PathParam("id") Long id, @Valid FuncionarioRequestDTO dto) {
        logger.info("Atualizando funcionário com ID {}: {}", id, dto);
        service.atualizar(id, dto);
        return Response.noContent().build();
    }

    @DELETE
    @Path("/{id}")
    @Transactional
    @RolesAllowed("FUNCIONARIO")
    public Response deletarFuncionario(@PathParam("id") Long id) {
        logger.info("Deletando funcionário com ID {}", id);
        service.deletar(id);
        return Response.noContent().build();
    }

}