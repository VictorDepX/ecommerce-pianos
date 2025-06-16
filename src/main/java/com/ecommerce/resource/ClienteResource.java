package com.ecommerce.resource;

import com.ecommerce.dto.ClienteRequestDTO;
import com.ecommerce.service.ClienteService;
import jakarta.annotation.security.RolesAllowed;
import jakarta.inject.Inject;
import jakarta.validation.Valid;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.Response;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Path("/clientes")
public class ClienteResource {

    @Inject
    ClienteService service;

    private static final Logger LOG = LoggerFactory.getLogger(ClienteResource.class);

    @GET
    @RolesAllowed("FUNCIONARIO")
    public Response listarTodos() {
        LOG.info("FUNCIONARIO solicitou listagem de todos os clientes.");
        return Response.ok().entity(service.listarTodos()).build();
    }

    @GET
    @Path("/{id}")
    @RolesAllowed({"FUNCIONARIO"})
    public Response buscarPorId(@PathParam("id") Long id) {
        LOG.info("Requisição para buscar cliente por ID: {}", id);
        return service.buscarPorId(id);
    }

    @POST
    public Response criarCliente(@Valid ClienteRequestDTO dto) {
        LOG.info("FUNCIONARIO criando novo cliente: {}", dto);
        return Response.status(Response.Status.CREATED).entity(service.salvar(dto)).build();
    }

    @PUT
    @Path("/{id}")
    @RolesAllowed({"FUNCIONARIO", "CLIENTE"})
    public Response atualizarCliente(@PathParam("id") Long id, @Valid ClienteRequestDTO dto) {
        LOG.info("Atualização solicitada para cliente ID: {}. DTO: {}", id, dto);
        service.atualizar(id, dto);
        return Response.noContent().build();
    }

    @DELETE
    @Path("/{id}")
    @RolesAllowed("FUNCIONARIO")
    public Response deletarCliente(@PathParam("id") Long id) {
        LOG.warn("FUNCIONARIO solicitou deleção do cliente ID: {}", id);
        service.deletar(id);
        return Response.noContent().build();
    }
}
