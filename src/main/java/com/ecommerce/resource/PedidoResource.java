package com.ecommerce.resource;

import com.ecommerce.dto.PedidoRequestDTO;
import com.ecommerce.service.PedidoService;
import jakarta.inject.Inject;
import jakarta.validation.Valid;
import jakarta.ws.rs.*;

@Path("/pedidos")
public class PedidoResource {

    @Inject PedidoService service;

    @GET
    public Object listarTodos() {
        return service.listarTodos();
    }

    @GET
    @Path("/{id}")
    public Object buscarPedido(@PathParam("id") Long id) {
        return service.buscarPorId(id);
    }

    @POST
    public void salvarPedido(@Valid PedidoRequestDTO dto) {
        service.salvar(dto);
    }

    @DELETE
    @Path("/{id}")
    public void deletarPedido(@PathParam("id") Long id) {
        service.deletar(id);
    }
}

