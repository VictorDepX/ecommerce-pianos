package com.ecommerce.resource;

import com.ecommerce.dto.MunicipioRequestDTO;
import com.ecommerce.service.MunicipioService;
import jakarta.inject.Inject;
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
    public Response buscarPorId(@PathParam("id") Long id) {
        return service.buscarPorId(id);
    }

    @POST
    public void salvarMunicipio(@Valid MunicipioRequestDTO dto) {
        service.salvar(dto);
    }

    @PUT
    @Path("/{id}")
    public void atualizarMunicipio(@PathParam("id") Long id, @Valid MunicipioRequestDTO dto) {
        service.atualizar(id, dto);
    }

    @DELETE
    @Path("/{id}")
    public void deletarMunicipio(@PathParam("id") Long id) {
        service.deletar(id);
    }
}
