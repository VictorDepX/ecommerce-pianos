package com.ecommerce.resource;

import com.ecommerce.dto.PedidoRequestDTO;
import com.ecommerce.service.PedidoService;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.Response;

@Path("/pedidos")
public class PedidoResource {

    @Inject PedidoService service;

    @GET
    public Response listarTodos() {
        return service.listarTodos();
    }

    @GET
    @Path("/{id}")
    public Response buscarPedido(@PathParam("id") Long id) {
        return service.buscarPorId(id);
    }

    @POST
    @Transactional
    public Response salvarPedido(@Valid PedidoRequestDTO dto) {
        return Response.status(Response.Status.CREATED).entity(service.salvar(dto)).build();
    }

    @DELETE
    @Path("/{id}")
    @Transactional
    public void deletarPedido(@PathParam("id") Long id) {
        service.deletar(id);
    }
}

