package com.ecommerce.resource;

import com.ecommerce.dto.MarcaRequestDTO;
import com.ecommerce.service.MarcaService;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

@Path("/marcas")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class MarcaResource {

    @Inject MarcaService service;

    @GET
    public Response listarTodos() {
        return service.listarTodos();
    }

    @GET
    @Path("/{id}")
    public Response buscarMarcas(@PathParam("id") Long id) {
        return service.buscarPorId(id);
    }

    @POST
    @Transactional
    public void criarMarcas(@Valid MarcaRequestDTO dto) {
        service.salvar(dto);
    }

    @PUT
    @Path("/{id}")
    @Transactional
    public void atualizarMarcas(@PathParam("id") Long id, @Valid MarcaRequestDTO dto) {
        service.atualizar(id, dto);
    }

    @DELETE
    @Path("/{id}")
    @Transactional
    public void deletarMarcas(@PathParam("id") Long id) {
        service.deletar(id);
    }
}
