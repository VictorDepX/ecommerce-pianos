package com.ecommerce.resource;

import com.ecommerce.dto.PianoDTO;
import com.ecommerce.service.PianoService;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;


@Path("/pianos")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
@ApplicationScoped
public class PianoResource {

    @Inject
    PianoService service;

    @GET
    public Response listarTodos() {
        return Response.ok().entity(service.listarTodos()).build();
    }

    @GET
    @Path("/{id}")
    public Response buscarPorId(@PathParam("id") Long id) {
        return Response.ok().entity(service.buscarPorId(id)).build();

    }

    @POST
    @Transactional
    public Response criarPiano(@Valid PianoDTO dto) {
        return Response.status(Response.Status.CREATED).entity(service.create(dto)).build();
    }

    @PUT
    @Path("/{id}")
    @Transactional
    public Response atualizarPiano(Long id, PianoDTO dto) {
        service.update(id, dto);
        return Response.noContent().build();
    }

    @DELETE
    @Path("/{id}")
    @Transactional
    public Response deletarPiano(Long id) {
        service.delete(id);
        return Response.noContent().build();
    }

    @GET
    @Path("fabricante/fabricante")
    public Response buscarPorFabricante(@QueryParam("value") String fabricante) {
        return service.buscarPorFabricante(fabricante);

    }
}