package com.ecommerce.resource;

import com.ecommerce.dto.EstadoRequestDTO;
import com.ecommerce.service.EstadoService;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

@Path("/estados")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class EstadoResource {

    @Inject
    EstadoService service;

    @GET
    public Response listarTodos() {
        return service.listarTodos();
    }

    @GET
    @Path("/{id}")
    public Response buscarPorId(@PathParam("id") Long id) {
        return service.buscarPorId(id);
    }

    @GET
    @Path("/buscar/sigla")
    public Response buscarPorSigla(@QueryParam("value") String sigla) {
        return service.buscarPorSigla(sigla);
    }

    @POST
    @Transactional
    public void criarEstado(@Valid EstadoRequestDTO dto) {
        service.salvar(dto);
    }

    @PUT
    @Path("/{id}")
    public void atualizarEstado(@PathParam("id") Long id, @Valid EstadoRequestDTO dto) {
        service.atualizar(id, dto);
    }

    @DELETE
    @Path("/{id}")
    public void deletarEstado(@PathParam("id") Long id) {
        service.deletar(id);
    }
}
