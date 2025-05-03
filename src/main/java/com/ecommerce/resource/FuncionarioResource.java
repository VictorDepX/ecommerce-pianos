package com.ecommerce.resource;
import com.ecommerce.dto.FuncionarioRequestDTO;
import com.ecommerce.service.FuncionarioService;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.Response;

@Path("/funcionarios")
public class FuncionarioResource {
    @Inject FuncionarioService service;

    @GET
    public Response listarTodos() {
        return Response.ok().entity(service.listarTodos()).build();
    }

    @GET
    @Path("/{id}")
    public Response buscarPorId(@PathParam("id") Long id) {
        return Response.ok().entity(service.buscarPorId(id)).build();
    }

    @POST
    @Transactional
    public Response criarFuncionario(@Valid FuncionarioRequestDTO dto) {
        return Response.status(Response.Status.CREATED).entity(service.salvar(dto)).build();
    }

    @PUT
    @Path("/{id}")
    @Transactional
    public Response atualizarFuncionario(@PathParam("id") Long id, @Valid FuncionarioRequestDTO dto) {
        service.atualizar(id, dto);
        return Response.noContent().build();
    }

    @DELETE
    @Path("/{id}")
    @Transactional
    public Response deletarFuncionario(@PathParam("id") Long id) {
        service.deletar(id);
        return Response.noContent().build();
    }
}