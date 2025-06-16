package com.ecommerce.resource;

import com.ecommerce.dto.MarcaRequestDTO;
import com.ecommerce.service.MarcaService;

import jakarta.annotation.security.RolesAllowed;
import jakarta.inject.Inject;
import jakarta.validation.Valid;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Path("/marcas")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class MarcaResource {

    private static final Logger logger = LoggerFactory.getLogger(MarcaResource.class);

    @Inject
    MarcaService service;

    @GET
    @RolesAllowed({"FUNCIONARIO"})
    public Response listarTodos() {
        logger.info("Listando todas as marcas");
        var marcas = service.listarTodos();
        return Response.ok(marcas).build();
    }

    @GET
    @Path("/{id}")
    @RolesAllowed({"FUNCIONARIO"})
    public Response buscarMarcas(@PathParam("id") Long id) {
        logger.info("Buscando marca com ID {}", id);
        var marca = service.buscarPorId(id);
        return Response.ok(marca).build();
    }

    @POST
    @RolesAllowed({"FUNCIONARIO"})
    public Response criarMarcas(@Valid MarcaRequestDTO dto) {
        logger.info("Criando nova marca: {}", dto);
        var marca = service.salvar(dto);
        return Response.status(Response.Status.CREATED).entity(marca).build();
    }

    @PUT
    @Path("/{id}")
    @RolesAllowed({"FUNCIONARIO"})
    public Response atualizarMarcas(@PathParam("id") Long id, @Valid MarcaRequestDTO dto) {
        logger.info("Atualizando marca com ID {} com dados: {}", id, dto);
        service.atualizar(id, dto);
        return Response.noContent().build();
    }

    @DELETE
    @Path("/{id}")
    @RolesAllowed({"FUNCIONARIO"})
    public Response deletarMarcas(@PathParam("id") Long id) {
        logger.info("Deletando marca com ID {}", id);
        service.deletar(id);
        return Response.noContent().build();
    }
}
