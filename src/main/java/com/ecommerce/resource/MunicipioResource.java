package com.ecommerce.resource;

import com.ecommerce.dto.MunicipioRequestDTO;
import com.ecommerce.service.MunicipioService;
import jakarta.annotation.security.RolesAllowed;
import jakarta.inject.Inject;
import jakarta.validation.Valid;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Path("/municipios")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class MunicipioResource {

    private static final Logger logger = LoggerFactory.getLogger(MunicipioResource.class);

    @Inject
    MunicipioService service;

    @GET
    @RolesAllowed({"FUNCIONARIO"})
    public Response listarTodos() {
        logger.info("Listando todos os municípios");
        var municipios = service.listarTodos();
        return Response.ok(municipios).build();
    }

    @GET
    @Path("/{id}")
    @RolesAllowed({"FUNCIONARIO"})
    public Response buscarPorId(@PathParam("id") Long id) {
        logger.info("Buscando município por ID {}", id);
        var municipio = service.buscarPorId(id);
        return Response.ok(municipio).build();
    }

    @POST
    @RolesAllowed({"FUNCIONARIO"})
    public Response criarMunicipio(@Valid MunicipioRequestDTO dto) {
        logger.info("Criando novo município: {}", dto);
        var municipio = service.salvar(dto);
        return Response.status(Response.Status.CREATED).entity(municipio).build();
    }

    @PUT
    @Path("/{id}")
    @RolesAllowed({"FUNCIONARIO"})
    public Response atualizarMunicipio(@PathParam("id") Long id, @Valid MunicipioRequestDTO dto) {
        logger.info("Atualizando município com ID {} com dados: {}", id, dto);
        service.atualizar(id, dto);
        return Response.noContent().build();
    }

    @DELETE
    @Path("/{id}")
    @RolesAllowed({"FUNCIONARIO"})
    public Response deletarMunicipio(@PathParam("id") Long id) {
        logger.info("Deletando município com ID {}", id);
        service.deletar(id);
        return Response.noContent().build();
    }
}
