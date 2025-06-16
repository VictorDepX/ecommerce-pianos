package com.ecommerce.resource;

import com.ecommerce.dto.EstadoRequestDTO;
import com.ecommerce.service.EstadoServiceImpl;
import jakarta.annotation.security.RolesAllowed;
import jakarta.inject.Inject;
import jakarta.validation.Valid;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Path("/estados")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class EstadoResource {

    private static final Logger logger = LoggerFactory.getLogger(EstadoResource.class);

    @Inject
    EstadoServiceImpl estadoService;

    @POST
    @RolesAllowed("FUNCIONARIO")
    public Response incluir(@Valid EstadoRequestDTO dto){
        logger.info("Incluindo estado: {}", dto);
        return Response.status(Response.Status.CREATED).entity(estadoService.salvar(dto)).build();
    }

    @PUT
    @Path("/{id}")
    @RolesAllowed("FUNCIONARIO")
    public Response atualizar(@PathParam("id") long id, @Valid EstadoRequestDTO dto){
        logger.info("Atualizando estado com ID {}: {}", id, dto);
        estadoService.atualizar(id, dto);
        return Response.noContent().build();
    }

    @DELETE
    @Path("/{id}")
    @RolesAllowed("FUNCIONARIO")
    public Response deletar(@PathParam("id") long id){
        logger.info("Deletando estado com ID {}", id);
        estadoService.deletar(id);
        return Response.noContent().build();
    }

    @GET
    @RolesAllowed({"FUNCIONARIO", "CLIENTE"})
    public Response buscarTodos(){
        logger.info("Buscando todos os estados");
        return Response.ok().entity(estadoService.listarTodos()).build();
    }

    @GET
    @Path("/{id}")
    @RolesAllowed({"FUNCIONARIO"})
    public Response buscarPorId(@PathParam("id") long id){
        logger.info("Buscando estado por ID {}", id);
        return Response.ok().entity(estadoService.buscarPorId(id)).build();
    }

    @GET
    @Path("/buscar/sigla")
    @RolesAllowed({"FUNCIONARIO", "CLIENTE"})
    public Response buscarPorSigla(@QueryParam("value") String sigla) {
        logger.info("Buscando estado por sigla: {}", sigla);
        return Response.ok().entity(estadoService.buscarPorSigla(sigla)).build();
    }
}
