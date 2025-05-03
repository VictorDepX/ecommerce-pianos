package com.ecommerce.resource;

import com.ecommerce.dto.FornecedorRequestDTO;
import com.ecommerce.service.FornecedorService;
import jakarta.inject.Inject;
import jakarta.validation.Valid;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

@Path("/fornecedores")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class FornecedorResource {

    @Inject
    FornecedorService service;

    @GET
    public Response listarTodos() {
        return service.listarTodos();    
    }

    @GET
    @Path("/{id}")
    public Response buscarPorID(@PathParam("id") Long id) {
        return service.buscarPorId(id);
    }

    @GET
    @Path("/buscar/nome")
    public Response buscarPorNome(@QueryParam("nome") String nome) {
        return Response.ok().entity(service.buscarPorNome(nome)).build();
    }

    @GET
    @Path("/buscar/cnpj")
    public Response buscarPorCnpj(@QueryParam("cnpj") String cnpj) {
        return Response.ok().entity(service.buscarPorCnpj(cnpj)).build();
    }

    @GET
    @Path("/buscar/telefone")
    public Response buscarPorTelefone(@QueryParam("") String telefone) {
        return Response.ok().entity(service.buscarPorTelefone(telefone)).build();
    }

    @POST
    public Response criarFornecedor(@Valid FornecedorRequestDTO dto) {
        return Response.status(Response.Status.CREATED).entity(service.salvar(dto)).build();
    }

    @PUT
    @Path("/{id}")
    public Response atualizarFornecedor(@PathParam("id") Long id, @Valid FornecedorRequestDTO dto) {
        service.atualizar(id, dto);
        return Response.noContent().build();
    }

    @DELETE
    @Path("/{id}")
    public Response deletarFornecedor(@PathParam("id") Long id) {
        service.deletar(id);
        return Response.noContent().build();
    }
}
