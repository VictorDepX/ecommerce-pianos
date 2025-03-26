package com.ecommerce.resource;

import com.ecommerce.dto.PianoDTO;
import com.ecommerce.dto.PianoResponseDTO;
import com.ecommerce.service.PianoService;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import java.util.List;

@Path("/pianos")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
@ApplicationScoped
class PianoResource {

    @Inject
    PianoService service;

    @GET
    public List<PianoResponseDTO> listarTodos() {
        return service.listarTodos();
    }

    @GET
    @Path("/{id}")
    public PianoResponseDTO buscarPorId(@PathParam("id") Long id) {
        return service.buscarPorId(id);
    }

    @POST
    public PianoResponseDTO criarPiano(PianoDTO dto) {
        return service.create(dto);
    }

    @PUT
    @Path("/{id}")
    public void atualizarPiano(Long id, PianoDTO dto) {
        service.update(id, dto);
    }

    @DELETE
    @Path("/{id}")
    @Transactional
    public void deletarPiano(Long id) {
        service.delete(id);
    }

    @GET
    @Path("/fabricante/{fabricante}")
    public PianoResponseDTO buscarPorFabricante(String fabricante) {
        return service.buscarPorFabricante(fabricante);
    }
}