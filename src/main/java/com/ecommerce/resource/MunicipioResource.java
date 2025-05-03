package com.ecommerce.resource;

import com.ecommerce.dto.MunicipioRequestDTO;
import com.ecommerce.service.MunicipioService;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

@Path("/municipios")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class MunicipioResource {

    @Inject
    MunicipioService service;

    @GET
    public Response listarTodos() {
        return service.listarTodos();
    }

    @GET
    @Path("/{id}")
    @Transactional
    public Response buscarPorId(@PathParam("id") Long id) {
        return Response.ok().entity(service.buscarPorId(id)).build();
    }

    @POST
    @Transactional
    public Response criarMunicipio(@Valid MunicipioRequestDTO dto) {
        return Response.status(Response.Status.CREATED).entity(service.salvar(dto)).build();
    }

    @PUT
    @Path("/{id}")
    @Transactional
    public void atualizarMunicipio(@PathParam("id") Long id, @Valid MunicipioRequestDTO dto) {
        service.atualizar(id, dto);
    }

    @DELETE
    @Path("/{id}")
    @Transactional
    public void deletarMunicipio(@PathParam("id") Long id) {
        service.deletar(id);
    }
}
