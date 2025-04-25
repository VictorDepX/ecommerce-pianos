package com.ecommerce.resource;

import com.ecommerce.dto.FornecedorRequestDTO;
import com.ecommerce.service.FornecedorService;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
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
    public Response buscarPorNome(@QueryParam("value") String nome) {
        return service.buscarPorNome(nome);
    }

    @GET
    @Path("/buscar/cnpj")
    public Response buscarPorCnpj(@QueryParam("value") String cnpj) {
        return service.buscarPorCnpj(cnpj);
    }

    @GET
    @Path("/buscar/telefone")
    public Response buscarPorTelefone(@QueryParam("value") String telefone) {
        return service.buscarPorTelefone(telefone);
    }

    @POST
    @Transactional
    public void criarFornecedor(@Valid FornecedorRequestDTO dto) {
        service.salvar(dto);
    }

    @PUT
    @Path("/{id}")
    public void atualizarFornecedor(@PathParam("id") Long id, @Valid FornecedorRequestDTO dto) {
        service.atualizar(id, dto);
    }

    @DELETE
    @Path("/{id}")
    public void deletarFornecedor(@PathParam("id") Long id) {
        service.deletar(id);
    }
}
