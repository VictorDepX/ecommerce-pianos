package com.ecommerce.resource;
import com.ecommerce.dto.ClienteRequestDTO;
import com.ecommerce.service.ClienteService;
import jakarta.inject.Inject;
import jakarta.validation.Valid;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.Response;

@Path("/clientes")
public class ClienteResource {
    @Inject ClienteService service;

    @GET
    public Response listarTodos() {
        return Response.ok().entity(service.listarTodos()).build();
    }

    @GET
    @Path("/{id}")
    public Response buscarPorId(@PathParam("id") Long id) {
        return service.buscarPorId(id);
    }

    @POST
    public Response criarCliente(@Valid ClienteRequestDTO dto) {
        return Response.status(Response.Status.CREATED).entity(service.salvar(dto)).build();
    }

    @PUT
    @Path("/{id}")
    public Response atualizarCliente(@PathParam("id") Long id, @Valid ClienteRequestDTO dto) {
        service.atualizar(id, dto);
        return Response.noContent().build();

    }

    @DELETE
    @Path("/{id}")
    public Response deletarCliente(@PathParam("id") Long id) {
        service.deletar(id);
        return Response.noContent().build();

    }
}