package com.ecommerce.resource;

import com.ecommerce.dto.PianoDTO;
import com.ecommerce.service.PianoService;
import jakarta.annotation.security.RolesAllowed;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Path("/pianos")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
@ApplicationScoped
public class PianoResource {

    private static final Logger logger = LoggerFactory.getLogger(PianoResource.class);

    @Inject
    PianoService service;

    @GET
    @RolesAllowed({"CLIENTE", "FUNCIONARIO"})
    public Response listarTodos() {
        logger.info("Listando todos os pianos");
        return Response.ok().entity(service.listarTodos()).build();
    }

    @GET
    @Path("/{id}")
    @RolesAllowed({"CLIENTE", "FUNCIONARIO"})
    public Response buscarPorId(@PathParam("id") Long id) {
        logger.info("Buscando piano por ID: {}", id);
        return Response.ok().entity(service.buscarPorId(id)).build();
    }

    @POST
    @Transactional
    @RolesAllowed("FUNCIONARIO")
    public Response criarPiano(@Valid PianoDTO dto) {
        logger.info("Criando novo piano: {}", dto);
        return Response.status(Response.Status.CREATED).entity(service.create(dto)).build();
    }

    @PUT
    @Path("/{id}")
    @Transactional
    @RolesAllowed("FUNCIONARIO")
    public Response atualizarPiano(@PathParam("id") Long id, @Valid PianoDTO dto) {
        logger.info("Atualizando piano ID {}: {}", id, dto);
        service.update(id, dto);
        return Response.noContent().build();
    }

    @DELETE
    @Path("/{id}")
    @Transactional
    @RolesAllowed("FUNCIONARIO")
    public Response deletarPiano(@PathParam("id") Long id) {
        logger.info("Deletando piano ID {}", id);
        service.deletar(id);
        return Response.noContent().build();
    }

    @GET
    @Path("/fabricante")
    @RolesAllowed({"CLIENTE", "FUNCIONARIO"})
    public Response buscarPorFabricante(@QueryParam("value") String fabricante) {
        logger.info("Buscando pianos por fabricante: {}", fabricante);
        return Response.ok().entity(service.buscarPorFabricante(fabricante)).build();
    }
}
