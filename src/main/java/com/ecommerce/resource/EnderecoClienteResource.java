package com.ecommerce.resource;

import com.ecommerce.dto.EnderecoClienteRequestDTO;
import com.ecommerce.service.EnderecoClienteService;
import jakarta.annotation.security.RolesAllowed;
import jakarta.inject.Inject;
import jakarta.validation.Valid;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Path("/enderecos-cliente")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class EnderecoClienteResource {

    private static final Logger logger = LoggerFactory.getLogger(EnderecoClienteResource.class);

    @Inject
    EnderecoClienteService service;

    @GET
    @RolesAllowed({"CLIENTE", "FUNCIONARIO"})
    public Response buscarPorNome(@QueryParam("nome") String nome) {
        logger.info("Buscando endereços por nome: {}", nome);
        return Response.ok(service.buscarPorNome(nome)).build();
    }

    @GET
    @Path("/{id}")
    @RolesAllowed({"CLIENTE", "FUNCIONARIO"})
    public Response buscarEndereco(@PathParam("id") Long id) {
        logger.info("Buscando endereço por ID: {}", id);
        return Response.ok(service.buscarPorId(id)).build();
    }

    @POST
    @RolesAllowed("CLIENTE")
    public Response salvarEndereco(@Valid EnderecoClienteRequestDTO dto) {
        logger.info("Salvando novo endereço: {}", dto);
        return Response.status(Response.Status.CREATED).entity(service.salvar(dto)).build();
    }

    @PUT
    @Path("/{id}")
    @RolesAllowed("CLIENTE")
    public Response atualizarEndereco(@PathParam("id") Long id, @Valid EnderecoClienteRequestDTO dto) {
        logger.info("Atualizando endereço ID {}: {}", id, dto);
        service.atualizar(id, dto);
        return Response.noContent().build();
    }

    @DELETE
    @Path("/{id}")
    @RolesAllowed("FUNCIONARIO")
    public Response deletarEndereco(@PathParam("id") Long id) {
        logger.info("Deletando endereço ID {}", id);
        service.deletar(id);
        return Response.noContent().build();
    }
}
