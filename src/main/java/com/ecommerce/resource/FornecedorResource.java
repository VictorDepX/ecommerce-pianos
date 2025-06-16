package com.ecommerce.resource;

import com.ecommerce.dto.FornecedorRequestDTO;
import com.ecommerce.service.FornecedorService;

import jakarta.annotation.security.RolesAllowed;
import jakarta.inject.Inject;
import jakarta.validation.Valid;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Path("/fornecedores")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class FornecedorResource {

    private static final Logger logger = LoggerFactory.getLogger(FornecedorResource.class);

    @Inject
    FornecedorService service;

    @GET
    @RolesAllowed({"FUNCIONARIO"})
    public Response listarTodos() {
        logger.info("Listando todos os fornecedores");
        var lista = service.listarTodos();
        return Response.ok(lista).build();
    }

    @GET
    @Path("/{id}")
    @RolesAllowed({"FUNCIONARIO"})
    public Response buscarPorID(@PathParam("id") Long id) {
        logger.info("Buscando fornecedor por ID {}", id);
        var fornecedor = service.buscarPorId(id);
        return Response.ok(fornecedor).build();
    }

    @GET
    @Path("/buscar/nome")
    @RolesAllowed({"FUNCIONARIO"})
    public Response buscarPorNome(@QueryParam("nome") String nome) {
        logger.info("Buscando fornecedor por nome {}", nome);
        var fornecedores = service.buscarPorNome(nome);
        return Response.ok(fornecedores).build();
    }

    @GET
    @Path("/buscar/cnpj")
    @RolesAllowed({"FUNCIONARIO"})
    public Response buscarPorCnpj(@QueryParam("cnpj") String cnpj) {
        logger.info("Buscando fornecedor por CNPJ {}", cnpj);
        var fornecedor = service.buscarPorCnpj(cnpj);
        return Response.ok(fornecedor).build();
    }

    @GET
    @Path("/buscar/telefone")
    @RolesAllowed({"FUNCIONARIO"})
    public Response buscarPorTelefone(@QueryParam("telefone") String telefone) {
        logger.info("Buscando fornecedor por telefone {}", telefone);
        var fornecedor = service.buscarPorTelefone(telefone);
        return Response.ok(fornecedor).build();
    }

    @POST
    @RolesAllowed({"FUNCIONARIO"})
    public Response criarFornecedor(@Valid FornecedorRequestDTO dto) {
        logger.info("Criando novo fornecedor: {}", dto);
        var fornecedor = service.salvar(dto);
        return Response.status(Response.Status.CREATED).entity(fornecedor).build();
    }

    @PUT
    @Path("/{id}")
    @RolesAllowed({"FUNCIONARIO"})
    public Response atualizarFornecedor(@PathParam("id") Long id, @Valid FornecedorRequestDTO dto) {
        logger.info("Atualizando fornecedor com ID {} com dados: {}", id, dto);
        service.atualizar(id, dto);
        return Response.noContent().build();
    }

    @DELETE
    @Path("/{id}")
    @RolesAllowed({"FUNCIONARIO"})
    public Response deletarFornecedor(@PathParam("id") Long id) {
        logger.info("Deletando fornecedor com ID {}", id);
        service.deletar(id);
        return Response.noContent().build();
    }
}
