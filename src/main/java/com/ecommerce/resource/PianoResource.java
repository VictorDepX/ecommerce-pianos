package com.ecommerce.resource;

import com.ecommerce.dto.PianoRequestDTO;
import com.ecommerce.dto.PianoResponseDTO;
import com.ecommerce.service.PianoService;

import io.quarkus.runtime.annotations.RegisterForReflection;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import java.util.List;

@RegisterForReflection
@Path("/pianos")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
@ApplicationScoped
class PianoResource {
    @Inject
    PianoService pianoService;

    public PianoResource() {

    }

    @GET
    public List<PianoResponseDTO> listarTodos() {
        return pianoService.listarTodos();
    }

    @GET
    @Path("/{id}")
    public PianoResponseDTO buscarPorId(@PathParam("id") Long id) {
        return pianoService.buscarPorId(id);
    }

    @POST
    public Response criarPiano(PianoRequestDTO dto) {
        PianoResponseDTO response = pianoService.criarPiano(dto);
        return Response.status(Response.Status.CREATED).entity(response).build();
    }

    @PUT
    @Path("/{id}")
    public PianoResponseDTO atualizarPiano(@PathParam("id") Long id, PianoRequestDTO dto) {
        return pianoService.atualizarPiano(id, dto);
    }

    @DELETE
    @Path("/{id}")
    public Response deletarPiano(@PathParam("id") Long id) {
        pianoService.deletarPiano(id);
        return Response.status(Response.Status.NO_CONTENT).build();
    }

    @GET
    @Path("/fabricante/{fabricante}")
    public List<PianoResponseDTO> buscarPorFabricante(@PathParam("fabricante") String fabricante) {
        return pianoService.buscarPorFabricante(fabricante);
    }
}